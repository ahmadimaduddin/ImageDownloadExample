package com.olele76.imagedownloadexample

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.olele76.imagedownloadexample.databinding.ActivityMainBinding
import com.olele76.imagedownloadexample.downloader.DownloaderWorker
import com.olele76.imagedownloadexample.downloader.ImageDownloader
import com.olele76.imagedownloadexample.filemanager.FileHelper
import com.olele76.imagedownloadexample.filemanager.FileWorker
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val editInput: EditText by lazy { binding.editTextInput }
    private val imageShow: ImageView by lazy { binding.imageView }
    private val buttonDownload: Button by lazy { binding.buttonDownload }
    private val buttonMoveToFilter: Button by lazy { binding.buttonMoveFilter }

    private lateinit var imageDownloader: ImageDownloader
    private lateinit var fileHelper: FileHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonDownload.setOnClickListener(downloadClicked)
        buttonMoveToFilter.setOnClickListener(filterClicked)

        inject()

        // editInput.setText("https://upload.wikimedia.org/wikipedia/commons/8/89/Tomato_je.jpg")
    }

    private lateinit var bitmapImage: Bitmap

    private val downloadClicked: View.OnClickListener = View.OnClickListener {
        val url: String = editInput.text.toString()
        CoroutineScope(Dispatchers.Main).launch {
            bitmapImage = imageDownloader.download(url)
            Glide.with(this@MainActivity)
                .load(bitmapImage)
                .apply(RequestOptions().override(400, 400))
                .into(imageShow)
        }
    }

    private val filterClicked: View.OnClickListener = View.OnClickListener {
        val uri: String? = fileHelper.saveImage(bitmapImage)
        val intent = Intent(this, FilterActivity::class.java)
        intent.putExtra(EXTRA_MESSAGE, uri)
        startActivity(intent)
    }

    private fun inject() {
        imageDownloader = DownloaderWorker(applicationContext)
        fileHelper = FileWorker()
    }

    companion object {
        const val EXTRA_MESSAGE = "com.olele76.imagedownloadexample.MESSAGE"
    }
}