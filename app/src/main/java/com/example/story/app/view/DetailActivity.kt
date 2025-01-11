package com.example.story.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.story.app.R
import com.example.story.app.data.remote.respon.story.ListStory
import com.example.story.app.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.titleDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fetchData()
        binding.iconBack.setOnClickListener{
            finish()
        }
    }

    @Suppress("DEPRECATION")
    private fun fetchData() {
        val i = intent.getParcelableExtra<ListStory>(EXTRA_ITEM)
        binding.apply {
            tvName.text = i?.name
            tvDescription.text = i?.description
            Glide.with(this@DetailActivity)
                .load(i?.photoUrl)
                .into(imgPhotos)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_ITEM = "extra_item"
    }
}