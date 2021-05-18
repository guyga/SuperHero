package com.example.android.superhero.domain

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.example.android.superhero.R
import com.example.android.superhero.database.SuperHeroDao
import com.example.android.superhero.database.SuperHeroDatabase
import com.example.android.superhero.database.model.UrlDatabaseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageLoader(private var superHeroDao: SuperHeroDao) {

    private val TAG = ImageLoader::class.simpleName

    suspend fun loadImage(imageView: ImageView, url: String) {
        withContext(Dispatchers.IO) {
            val cachedUrlWrapper = superHeroDao.getUrlWrapper(url)
            if (cachedUrlWrapper == null || isExpired(cachedUrlWrapper)) {
                Log.i(TAG, "Cached url is being saved and loaded")
                val newUrlWrapper = UrlDatabaseEntity(url, System.currentTimeMillis())
                loadImage(imageView, newUrlWrapper)
                superHeroDao.insertUrlWrapper(newUrlWrapper)
            } else {
                Log.i(TAG, "Cached url is valid. Loading now")
                loadImage(imageView, cachedUrlWrapper)
            }
        }
    }

    private fun isExpired(urlWrapper: UrlDatabaseEntity): Boolean {
        val currentTimeStamp = System.currentTimeMillis()
        val isExpired = currentTimeStamp - urlWrapper.timestamp > getAllowedCachingDuration()
        Log.i(TAG, "isExpired = $isExpired")
        return isExpired
    }

    private fun getAllowedCachingDuration(): Long {
        return 24 * 60 * 60 * 1000 // 24 hours in millis
//        return 60 * 1000 // 1 minute duration
    }

    private suspend fun loadImage(imageView: ImageView, urlWrapper: UrlDatabaseEntity) {
        withContext(Dispatchers.Main) {
            Glide.with(imageView)
                .load(urlWrapper.url)
                .signature(ObjectKey(urlWrapper.timestamp.toString()))
                .apply(
                    RequestOptions()
                        .transform(CenterInside(), RoundedCorners(16))
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(imageView)
        }
    }

    companion object {
        private lateinit var INSTANCE: ImageLoader

        fun getInstance(context: Context): ImageLoader {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE =
                        ImageLoader(
                            SuperHeroDatabase.getDatabase(context).superHeroDao()
                        )
                }
                return INSTANCE
            }
        }
    }
}