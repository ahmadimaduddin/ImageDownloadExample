package com.olele76.imagedownloadexample.downloader

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DownloaderWorker(val context: Context) : ImageDownloader {
    override suspend fun download(url: String): Bitmap =
        withContext(Dispatchers.IO) {
            Glide.with(context)
                .asBitmap()
                .load(url)
                .submit()
                .get()
        }
}