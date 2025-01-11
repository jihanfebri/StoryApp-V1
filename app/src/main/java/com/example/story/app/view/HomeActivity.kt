package com.example.story.app.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.story.app.R
import com.example.story.app.adapter.StoryAdapter
import com.example.story.app.data.DataRepository
import com.example.story.app.data.remote.api.ApiClient
import com.example.story.app.data.remote.respon.story.ListStory
import com.example.story.app.databinding.ActivityHomeBinding
import com.example.story.app.util.NetworkResult
import com.example.story.app.util.PrefsManager
import com.example.story.app.util.ViewModelFactory

class HomeActivity : AppCompatActivity(), StoryAdapter.OnItemClickAdapter {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private lateinit var prefsManager: PrefsManager
    private lateinit var viewModel: HomeViewModel
    private lateinit var storyAdapter: StoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        prefsManager = PrefsManager(this)
        storyAdapter = StoryAdapter(this, this)
        val dataRepository = DataRepository(ApiClient.create())
        viewModel = ViewModelProvider(this, ViewModelFactory(dataRepository))[HomeViewModel::class.java]
        fetchData(prefsManager.token)

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            fetchData(prefsManager.token)
        }

        binding.btnTry.setOnClickListener {
            setLoadingState(true)
            fetchData(prefsManager.token)
        }

        binding.fbAddStory.setOnClickListener {
            startActivity(Intent(this, AddStoryActivity::class.java))
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.account_menu -> {
                    val intent = Intent(this, SettingActivity::class.java)
                    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
                    true
                }
                else -> false
            }
        }
    }

    private fun fetchData(auth: String) {
        binding.rvStory.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = storyAdapter
        }
        viewModel.apply {
            setLoadingState(true)
            fetchListStory(auth)
            responseListStory.observe(this@HomeActivity) {
                when(it) {
                    is NetworkResult.Success -> {
                        if(it.data?.listStory != null) {
                            storyAdapter.setData(it.data.listStory)
                            binding.btnTry.visibility = View.GONE
                        } else {
                            binding.btnTry.visibility = View.GONE
                            binding.rvStory.visibility = View.GONE
                            binding.tvNotFound.visibility = View.VISIBLE
                        }
                        binding.tvError.visibility = View.GONE
                        setLoadingState(false)
                        binding.swipeRefresh.isRefreshing = false
                    }
                    is NetworkResult.Loading -> {
                        setLoadingState(true)
                        binding.swipeRefresh.isRefreshing = true
                    }
                    is NetworkResult.Error -> {
                        setLoadingState(false)
                        binding.rvStory.visibility = View.GONE
                        binding.tvNotFound.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.btnTry.visibility = View.VISIBLE
                        binding.swipeRefresh.isRefreshing = false
                    }
                }
            }

        }
    }

    override fun onItemClicked(listStory: ListStory, optionsCompat: ActivityOptionsCompat) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ITEM, listStory)
        startActivity(intent, optionsCompat.toBundle())
    }

    private fun setLoadingState(loading: Boolean) {
        binding.rvStory.visibility = if (loading) View.GONE else View.VISIBLE
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }
}
