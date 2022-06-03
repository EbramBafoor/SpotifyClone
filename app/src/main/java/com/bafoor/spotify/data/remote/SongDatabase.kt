package com.bafoor.spotify.data.remote

import com.bafoor.spotify.data.entities.Song
import com.bafoor.spotify.utilities.Constant.SONG_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class SongDatabase {

    private val fireStore = FirebaseFirestore.getInstance()
    private val songCollection = fireStore.collection(SONG_COLLECTION)

    suspend fun getAllSongs() : List<Song> {
        return try {
            songCollection.get().await().toObjects(Song::class.java)
        } catch (e : Exception) {
            emptyList()
        }
    }
}