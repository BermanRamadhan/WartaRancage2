package com.uninet.wartarancage

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.uninet.wartarancage.WriteNewsActivity.Companion.REQUEST_IMAGE_CAPTURE
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class WriteNewsActivity : AppCompatActivity() {


    lateinit var currentPhotoPath: String
    lateinit var imageView: ImageView
    val BASE_URL = "http://localhost:9000/"

    companion object{
        const val CREATE_FILE = 1
        const val REQUEST_IMAGE_CAPTURE = 1
        private const val STATE_RESULT = "state_result"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_news)

        imageView = findViewById(R.id.gambar__berita)
//        val btn: Button = findViewById(R.id.btn_camera)

        //var viewmodel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        imageView?.setOnClickListener {
            //viewmodel.selectImage()
            selectImage()
        }
//
//        if(savedInstanceState != null){
//            val result = savedInstanceState.getString(STATE_RESULT)
//            //imageView.setImageBitmap(result)
//        }

    }

//    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
//        super.onSaveInstanceState(outState, outPersistentState)
//    }

    private fun selectImage() {
        val option: Array<String> = arrayOf("Take Photo", "Choose from Gallery", "Cancel")
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Add Photo")
        builder.setItems(option, DialogInterface.OnClickListener { dialog, item ->
            if (option.get(item).equals("Take Photo")) {
                dispatchTakePictureIntent()
            } else if (option.get(item).equals("Choose from Gallery")) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                Log.d("MainActivity", "selectImage: this${intent}")
                startActivityForResult(intent, 2)
            } else if (option.get(item).equals("Cancel")) {
                dialog.dismiss()
            }
        })
        builder.show()
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                            this,
                            "com.uninet.wartarancage.provider",
                            it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE)
                    Log.d("MainActivity", "dispatchTakePictureIntent: URI file nya ${photoURI}")
                    Log.d("MainActivity", "dispatchTakePictureIntent: Photo file nya ${photoFile}")
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }






    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            val takenImage = BitmapFactory.decodeFile(currentPhotoPath)
            imageView.setImageBitmap(takenImage)
            Log.d("MainActivity", "onActivityResult: this is taken image $takenImage")
        }else if (requestCode == 2 && resultCode == Activity.RESULT_OK){
//            val takenImage = data?.extras?.get("data") as Bitmap
//            imageView.setImageBitmap(takenImage)
            val selectedImage: Uri? = data?.data
            Log.d("MainActivity", "onActivityResult: uri data di galery $selectedImage")
            //val thumbnail = BitmapFactory.decodeFile(selectedImage)

            imageView.setImageURI(selectedImage)
        }
    }

}