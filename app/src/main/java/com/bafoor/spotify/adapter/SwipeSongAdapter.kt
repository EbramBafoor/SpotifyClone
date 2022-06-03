package com.bafoor.spotify.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import com.bafoor.spotify.R

import kotlinx.android.synthetic.main.song_list_item.view.*

class SwipeSongAdapter : BaseSongAdapter(R.layout.swip_item) {


    override val differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.itemView.apply {
            val text = "${song.title} - ${song.subtitle}"
            songTitleTv.text = text

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }
        }
    }
}