package com.kozitskiy.shoppinglist.management.handlers

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.kozitskiy.shoppinglist.*
import com.kozitskiy.shoppinglist.interfaces.PurchaseReceivers

abstract class PermissionsHandler(private val fragment: Fragment) :
    PurchaseReceivers.OnAccessPermissions {

    private val activity = fragment.requireActivity()
    private val fragmentContext = fragment.requireContext()

    companion object {
        private const val REQUEST_CODE_STORAGE_RATIONALE = 3
        private const val REQUEST_CODE_STORAGE = 4

        private const val REQUEST_CODE_CAMERA_RATIONALE = 5
        private const val REQUEST_CODE_CAMERA = 6

        private const val PERMISSION_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
        private const val PERMISSION_CAMERA = Manifest.permission.CAMERA
    }

    //Alert Dialog message with rationale importance access to this permission
    private fun showRationaleForStorage() {
        AlertDialog.Builder(fragmentContext)
            .setTitle(PERMISSION_DIALOG_TITLE)
            .setMessage(PERMISSION_DIALOG_RATIONALE_MSG)
            .setPositiveButton(PERMISSION_DIALOG_RATIONALE_POS_BTN) { _, _ ->
                fragment.requestPermissions(
                    arrayOf(
                        PERMISSION_STORAGE
                    ),
                    REQUEST_CODE_STORAGE_RATIONALE
                )
            }
            .setNegativeButton(PERMISSION_DIALOG_NEG_BTN, null)
            .create().show()
    }

    //Alert Dialog message with rationale importance access to this permission
    private fun showRationaleForCamera() {
        AlertDialog.Builder(fragmentContext)
            .setTitle(PERMISSION_DIALOG_TITLE)
            .setMessage(PERMISSION_DIALOG_RATIONALE_MSG)
            .setPositiveButton(PERMISSION_DIALOG_RATIONALE_POS_BTN) { _, _ ->
                fragment.requestPermissions(
                    arrayOf(
                        PERMISSION_CAMERA
                    ),
                    REQUEST_CODE_CAMERA_RATIONALE
                )
            }
            .setNegativeButton(PERMISSION_DIALOG_NEG_BTN, null)
            .create().show()
    }

    //Alert Dialog message with rationale importance access to this permission and ask about turn it by own using settings
    private fun showLastChance() {
        AlertDialog.Builder(fragmentContext)
            .setTitle(PERMISSION_DIALOG_TITLE)
            .setMessage(PERMISSION_DIALOG_LAST_CHANCE_MSG)
            .setPositiveButton(PERMISSION_DIALOG_LAST_CHANCE_POS_BTN) { _, _ ->
                fragmentContext.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", fragmentContext.packageName, null)
                })
            }
            .setNegativeButton(PERMISSION_DIALOG_NEG_BTN, null)
            .create().show()
    }

    //Checking permission for camera access
    fun checkCameraPermission() = when {
        hasPermission(fragmentContext,
            PERMISSION_CAMERA
        ) -> cameraGranted()
        shouldShowRationale(PERMISSION_CAMERA) -> showRationaleForCamera()
        else -> requestCamera()
    }

    //Checking permission for storage access
    fun checkStoragePermission() = when {
        hasPermission(fragmentContext,
            PERMISSION_STORAGE
        ) -> storageGranted()
        shouldShowRationale(
            PERMISSION_STORAGE
        ) -> showRationaleForStorage()
        else -> requestStorage()
    }

    private fun requestStorage() {
        fragment.requestPermissions(arrayOf(PERMISSION_STORAGE),
            REQUEST_CODE_STORAGE
        )
    }

    private fun requestCamera() {
        fragment.requestPermissions(arrayOf(PERMISSION_CAMERA),
            REQUEST_CODE_CAMERA
        )
    }

    //On permissions result
    override fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (grantResults.isNotEmpty()) {

            if (requestCode == REQUEST_CODE_STORAGE || requestCode == REQUEST_CODE_STORAGE_RATIONALE) {
                if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                    storageGranted()
                } else if (!shouldShowRationale(PERMISSION_STORAGE) && requestCode != REQUEST_CODE_STORAGE_RATIONALE) {
                    showLastChance()
                }
            }

            if (requestCode == REQUEST_CODE_CAMERA || requestCode == REQUEST_CODE_CAMERA_RATIONALE) {
                if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                    cameraGranted()
                } else if (!shouldShowRationale(PERMISSION_CAMERA) && requestCode != REQUEST_CODE_CAMERA_RATIONALE) {
                    showLastChance()
                }
            }
        }
    }

    private fun hasPermission(ctx: Context, permission: String) =
        ContextCompat.checkSelfPermission(ctx, permission) == PackageManager.PERMISSION_GRANTED

    private fun shouldShowRationale(permission: String) =
        ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)

    //Described in child class
    abstract fun storageGranted()

    abstract fun cameraGranted()
}