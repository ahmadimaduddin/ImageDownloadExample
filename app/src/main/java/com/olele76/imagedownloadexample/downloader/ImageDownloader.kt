package com.olele76.imagedownloadexample.downloader

import android.graphics.Bitmap

interface ImageDownloader {
    suspend fun download(url:String): Bitmap
}