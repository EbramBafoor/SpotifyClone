package com.bafoor.spotify.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bafoor.spotify.R
import com.bafoor.spotify.adapter.SongAdapter
import com.bafoor.spotify.utilities.Resource
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.fragment_all_song.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.allSongsProgressBar
import kotlinx.android.synthetic.main.fragment_home.rvHome
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var glide : RequestManager
    val songAdapter: SongAdapter = SongAdapter(glide)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        setupRecyclerView()
        subscribeToObservers()

        songAdapter.setItemClickListener {
            mainViewModel.playOrToggleSong(it)
        }
    }

    private fun setupRecyclerView() = rvHome.apply {
        adapter = songAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToObservers() {
        mainViewModel.mediaItems.observe(viewLifecycleOwner) { result ->

            when (result) {
                is Resource.Success -> {
                    allSongsProgressBar.visibility = View.INVISIBLE
                    result.data?.let {
                        songAdapter.songs = it
                    }
                }
                is Resource.Error -> Unit

                is Resource.Loading -> allSongsProgressBar.visibility = View.VISIBLE
            }
        }
    }
}