package com.olele76.imagedownloadexample

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.olele76.imagedownloadexample.databinding.ActivityFilterBinding
import com.olele76.imagedownloadexample.filemanager.FileHelper
import com.olele76.imagedownloadexample.filemanager.FileWorker
import com.olele76.imagedownloadexample.filter.ImageFilter
import com.olele76.imagedownloadexample.filter.ImageFilterWorker

class FilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilterBinding
    private val imageShow: ImageView by lazy { binding.imageViewShow }
    private val buttonFilter: Button by lazy { binding.buttonFilter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonFilter.setOnClickListener(filterClicked)

        inject()

        val imagePath = intent.getStringExtra(EXTRA_MESSAGE)
        val imageLoaded: Bitmap = fileHelper.loadImage(imagePath.toString())

        Glide.with(this)
            .load(imageLoaded)
            .apply(RequestOptions().override(400, 400))
            .into(imageShow)
    }

    private val filterClicked: View.OnClickListener = View.OnClickListener {
        val drawable = imageShow.drawable as BitmapDrawable
        val bitmap = drawable.bitmap
        val grayScaled = imageFilter.toGrayScale(bitmap)

        Glide.with(this)
            .load(grayScaled)
            .apply(RequestOptions().override(400, 400))
            .into(imageShow)
    }

    private lateinit var fileHelper: FileHelper
    private lateinit var imageFilter: ImageFilter

    private fun inject() {
        fileHelper = FileWorker()
        imageFilter = ImageFilterWorker()
    }

    companion object {
        const val EXTRA_MESSAGE = "com.olele76.imagedownloadexample.MESSAGE"
    }
}