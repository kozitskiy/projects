package com.kozitskiy.shoppinglist.management.handlers

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import java.io.FileNotFoundException
import java.io.InputStream
import javax.inject.Inject

class ImageLoadHandler @Inject constructor(private val fragment: Fragment) : PermissionsHandler(fragment) {

    //Set request codes for Intents we have
    companion object {
        private const val GALLERY_REQUEST_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }

    //Start intent (choose image from gallery)
    override fun storageGranted() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        fragment.startActivityForResult(
            galleryIntent,
            GALLERY_REQUEST_CODE
        )
    }

    //Start intent (take photo with camera)
    override fun cameraGranted() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        fragment.startActivityForResult(
            cameraIntent,
            CAMERA_REQUEST_CODE
        )
    }

    //Image uploader activation with alert dialog to choose action before
    fun activateImageUploader() {
        val chooserDialog = AlertDialog.Builder(fragment.requireContext())
        chooserDialog.setTitle("Choose Image")
        val imageDialogItems = arrayOf("Camera", "Gallery")
        chooserDialog.setItems(imageDialogItems) { _, which ->
            when (which) {
                0 -> checkCameraPermission()
                1 -> checkStoragePermission()
            }
        }
        chooserDialog.show()
    }

    //Getting bitmap instances
    private fun getBitmapFromCamera(data: Intent): Bitmap? = data.extras?.get("data") as Bitmap

    private fun getBitmapFromGallery(data: Intent): Bitmap? {
        val imageStream: InputStream?
        try {
            imageStream = fragment.requireActivity().contentResolver.openInputStream(data.data!!)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return null
        }
        return BitmapFactory.decodeStream(imageStream)
    }

    //Return bitmap instance which was received from user action if it exists
    fun getLoadedBitmap(requestCode: Int, resultCode: Int, data: Intent?): Bitmap? {
        return if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> getBitmapFromGallery(data)
                CAMERA_REQUEST_CODE -> getBitmapFromCamera(data)
                else -> null
            }
        } else null
    }
}