package com.bafoor.spotify.di

import android.content.Context
import com.bafoor.spotify.R
import com.bafoor.spotify.adapter.SongAdapter
import com.bafoor.spotify.adapter.SwipeSongAdapter
import com.bafoor.spotify.exoPlayer.MusicServiceConnection
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideSwipeSongAdapter() = SwipeSongAdapter()

    @Provides
    @Singleton
    fun provideMusicServiceConnection(
        @ApplicationContext context: Context
    ) = MusicServiceConnection(context)

    @Provides
    @Singleton
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) : RequestManager {
        return Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
    }
}












