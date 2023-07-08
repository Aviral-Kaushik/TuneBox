package com.aviral.tunebox.data.remote

import android.util.Log
import com.aviral.tunebox.data.entities.Song
import com.aviral.tunebox.other.Constants.SONG_COLLECTION
import com.aviral.tunebox.other.Constants.TAG
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MusicDatabase {

    private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(SONG_COLLECTION)

    suspend fun getAllSongs(): List<Song> {
        return try {
            songCollection.get().await().toObjects(Song::class.java)
        } catch (e: Exception) {
            Log.d(TAG, "getAllSongs: Exception while getting songs: ${e.message}")
            emptyList()
        }
    }

}