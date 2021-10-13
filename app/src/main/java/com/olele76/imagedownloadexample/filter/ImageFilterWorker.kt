package com.olele76.imagedownloadexample.filter

import android.graphics.*

class ImageFilterWorker:ImageFilter {
    override fun toGrayScale(input: Bitmap): Bitmap {
        val bmp = input.copy(Bitmap.Config.ARGB_8888, true)
        val bmpGrayscale = Bitmap.createBitmap(bmp.width, bmp.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmpGrayscale)
        val paint = Paint()
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f)
        val colorMatrixFilter = ColorMatrixColorFilter(colorMatrix)
        paint.colorFilter = colorMatrixFilter
        canvas.drawBitmap(bmp, 0F, 0F, paint)
        return bmpGrayscale
    }
}