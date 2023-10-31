package com.devhp.music_youtuyou_app.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.devhp.music_youtuyou_app.R
import com.devhp.music_youtuyou_app.data.model.YoutubeVideo
import com.devhp.music_youtuyou_app.databinding.YoutubeItemBinding

class YoutubeVideoAdapter : RecyclerView.Adapter<YoutubeVideoViewHolder>() {

    private val youtubeVideos = ArrayList<YoutubeVideo>()

    fun setYoutubeVideos(newYoutubeVideos: List<YoutubeVideo>) {
        youtubeVideos.clear()
        youtubeVideos.addAll(newYoutubeVideos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeVideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = YoutubeItemBinding.inflate(layoutInflater, parent, false)
        return YoutubeVideoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return youtubeVideos.size
    }

    override fun onBindViewHolder(holder: YoutubeVideoViewHolder, position: Int) {
        holder.bind(youtubeVideos[position])
    }

}

class YoutubeVideoViewHolder(private val binding: YoutubeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(youtubeVideo: YoutubeVideo) {
        binding.tvVideoName.text = youtubeVideo.videoName
        binding.cardYoutubeVideo.setOnClickListener {
            val bundle = bundleOf("url" to youtubeVideo.url)
            it.findNavController().navigate(R.id.action_homeFragment_to_webViewFragment, bundle)
        }
    }

}