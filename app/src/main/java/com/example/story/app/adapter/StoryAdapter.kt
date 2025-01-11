package com.example.story.app.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.story.app.data.remote.respon.story.ListStory
import com.example.story.app.databinding.StoryCardBinding

class StoryAdapter(private val context: Context, private val clickListener: OnItemClickAdapter) :
    RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    private val listStory = ArrayList<ListStory>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ListStory>) {
        listStory.clear()
        listStory.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StoryViewHolder(
        StoryCardBinding.inflate(LayoutInflater.from(context), parent, false)
    )

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(listStory[position])
    }

    override fun getItemCount(): Int = listStory.size

    inner class StoryViewHolder(private val binding: StoryCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listStory: ListStory) {
            with(binding) {
                tvUsername.text = listStory.name
                tvDescription.text = listStory.description
                Glide.with(context)
                    .load(listStory.photoUrl)
                    .into(imgPhoto)
                root.setOnClickListener {
                    val optionsCompat: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        context as Activity,
                        Pair(binding.imgPhoto, "image"),
                        Pair(binding.tvUsername, "username"),
                        Pair(binding.tvDescription, "description")
                    )
                    clickListener.onItemClicked(listStory, optionsCompat)
                }
            }
        }
    }

    interface OnItemClickAdapter {
        fun onItemClicked(listStory: ListStory, optionsCompat: ActivityOptionsCompat)
    }
}