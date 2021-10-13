package com.olele76.imagedownloadexample.filemanager

import android.graphics.Bitmap

interface FileHelper {
    fun saveImage(image: Bitmap):String?
    fun loadImage(uri:String):Bitmap
}