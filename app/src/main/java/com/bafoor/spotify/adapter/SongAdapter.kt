package com.bafoor.spotify.adapter


import androidx.recyclerview.widget.AsyncListDiffer
import com.bafoor.spotify.R
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.song_list_item.view.*
import javax.inject.Inject

class SongAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseSongAdapter(R.layout.song_list_item) {

    override var differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.itemView.apply {
            songTitleTv.text = song.title
            songSubTitleTv.text = song.subtitle
            glide.load(song.coverUrl).into(songItemImg)

           setOnClickListener {
               onItemClickListener?.let { click ->
                   click(song)
               }
           }
        }
    }

}






















