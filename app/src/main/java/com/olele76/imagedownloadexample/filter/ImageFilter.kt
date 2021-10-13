package com.olele76.imagedownloadexample.filter

import android.graphics.Bitmap

interface ImageFilter {
    fun toGrayScale(input:Bitmap):Bitmap
}