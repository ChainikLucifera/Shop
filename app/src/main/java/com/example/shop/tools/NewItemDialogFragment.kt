package com.example.shop.tools

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.shop.databinding.FragmentNewItemBinding


/**
 * Отдельный [DialogFragment] для добавления продуктов
 */
class NewItemDialogFragment : DialogFragment() {

    interface NewItemListener {
        fun onSubmit(name: String, uri: Uri?)
    }

    private lateinit var binding: FragmentNewItemBinding
    private var listener: NewItemListener? = null

    private lateinit var editText: EditText
    private lateinit var btnSubmit: Button
    private lateinit var btnImage: Button
    private lateinit var imageView: ImageView
    private var link: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = binding.editText
        val btnSubmit = binding.btnSubmit
        val btnImage = binding.btnImage
        val imageView = binding.ImageView


        val pickImageLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    requireContext().contentResolver.takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    link = uri
                    imageView.setImageURI(link)
                    Log.d("TEST", "Link to image: $uri")
                }
            }

        var requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        )
        { isGranted ->
            if (isGranted) {
                pickImageLauncher.launch("image/*")
            } else {
                Log.d("TEST", "You must allow permission for gallery")
            }
        }

        btnSubmit.setOnClickListener {
            val inputName = editText.text.toString()

            listener?.onSubmit(inputName, link)

            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0) //закрывает клавиатуру, из-за
            // которой возникала ошибка

            dismiss()
        }

        btnImage.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
        }
        //https://www.youtube.com/watch?v=nOtlFl1aUCw - о том как поставить картинку
    }

    fun setListener(listener: NewItemListener) {
        this.listener = listener
    }
}




