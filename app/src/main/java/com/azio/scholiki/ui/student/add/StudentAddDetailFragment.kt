package com.azio.scholiki.ui.student.add

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.azio.scholiki.R
import com.azio.scholiki.app.ScholikiAppConstants
import com.azio.scholiki.util.ImageUtil
import com.azio.scholiki.util.RequestPermissionForImages
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_student_add_detail.*
import java.io.File


class StudentAddDetailFragment : Fragment() {

    private var file: File? = null
    private val SELECT_FILE = 1
    private val CAPTURE_IMAGE = 450
    private var userChosenTask: Int = 0
    private var data: Intent? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageStudentProfile.setOnClickListener {
            val items = arrayOf<CharSequence>(
                resources.getString(R.string.item_label_takePhoto),
                resources.getString(R.string.item_label_chooseFromLibrary)
            )
            val builder = AlertDialog.Builder(this@StudentAddDetailFragment.context)
            builder.setTitle("Add Photo")
            builder.setNegativeButton(resources.getString(R.string.item_label_cancel)) { dialog, id -> dialog.cancel() }
            builder.setItems(items) { dialog, item ->
                val result = this@StudentAddDetailFragment.context?.let {
                    RequestPermissionForImages.checkPermissionForExternalStorage(
                        it
                    )
                }

                if (item == 0) {
                    userChosenTask = 0
                    if (result!!) {
                        cameraIntent()
                    }
                } else if (item == 1) {
                    userChosenTask = 1
                    if (result!!) {
                        galleryIntent()
                    }

                }
            }
            builder.show()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_add_detail, container, false)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            ScholikiAppConstants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (userChosenTask == 1)
                    galleryIntent()
            } else {
                //code for deny
            }
            ScholikiAppConstants.MY_PERMISSIONS_REQUEST_CAMERA -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (userChosenTask == 0)
                    cameraIntent()
            } else {
            }
        }
    }


    private fun galleryIntent() {
        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        val intent = Intent(Intent.ACTION_PICK)

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        /*
        intent.addCategory(Intent.CATEGORY_OPENABLE);
*/


        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.type = "image/*"
        startActivityForResult(intent, SELECT_FILE)
    }


    private fun cameraIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(activity!!.packageManager) != null) {
            // Create the File where the photo should go
            var photoFile: File? = null
            try {
                photoFile = activity?.let { ImageUtil.createImageFile(it) }
                file = photoFile
            } catch (ex: Exception) {
                // Error occurred while creating the File
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                val photoURI = this@StudentAddDetailFragment.context?.let {
                    FileProvider.getUriForFile(
                        it,
                        "com.azio.scholiki.fileprovider",
                        photoFile
                    )
                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, CAPTURE_IMAGE)
            }
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data!!)
                val imgUtil = activity?.let { ImageUtil(it) }
                file = imgUtil?.dumpImageMetaData(data.data)

            } else if (requestCode == CAPTURE_IMAGE && resultCode == Activity.RESULT_OK) {
                onCaptureImageResult(data)
            }
            this.data = data
        }


    }

    private fun onSelectFromGalleryResult(data: Intent) {
        Glide.with(this).load(data.data).apply(RequestOptions.circleCropTransform()).into(imageStudentProfile)
    }

    private fun onCaptureImageResult(data: Intent?) {
        if (file != null) {
            Glide.with(this)
                .load(file)
                .apply(RequestOptions.circleCropTransform())
                .into(imageStudentProfile)
        }

    }

}



