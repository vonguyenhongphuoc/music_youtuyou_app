package com.devhp.music_youtuyou_app.presentation.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.devhp.music_youtuyou_app.data.model.YoutubeVideo
import com.devhp.music_youtuyou_app.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: YoutubeVideoAdapter
    private val handler = Handler()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initYoutubeVideosRecyclerView()
        return binding.root
    }

    private fun initYoutubeVideosRecyclerView() {
        binding.rcvYoutubeVideo.layoutManager = LinearLayoutManager(requireActivity())
        adapter = YoutubeVideoAdapter()
        binding.rcvYoutubeVideo.adapter = adapter
        displayYoutubeVideos()
    }

    private fun displayYoutubeVideos() {
        binding.tvProgressBar.visibility = View.VISIBLE
        val youtubeVideos = ArrayList<YoutubeVideo>()
        youtubeVideos.add(
            YoutubeVideo(
                "Trách duyên trách phận lofi",
                "https://www.youtube.com/watch?v=jfmBPtRi5So"
            )
        )
        youtubeVideos.add(
            YoutubeVideo(
                "Cắt đôi nỗi sầu lofi",
                "https://www.youtube.com/watch?v=dpUiDBQN06c"
            )
        )
        youtubeVideos.add(
            YoutubeVideo(
                "Phim mới",
                "https://phimmoiyyy.net/"
            )
        )

        handler.postDelayed({
            adapter.setYoutubeVideos(youtubeVideos)
            binding.tvProgressBar.visibility = View.GONE
        }, 0)


    }
}