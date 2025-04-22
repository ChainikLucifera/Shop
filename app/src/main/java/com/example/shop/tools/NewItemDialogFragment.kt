package com.example.shop.tools

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.shop.R


/**
 * Отдельный [DialogFragment] для добавления продуктов
 */
class NewItemDialogFragment : DialogFragment() {

    interface NewItemListener {
        fun onSubmit(name: String)
    }

    private var listener: NewItemListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = view.findViewById<EditText>(R.id.editText)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val inputName = editText.text.toString()
            listener?.onSubmit(inputName)

            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0) //закрывает клавиатуру, из-за
            // которой возникала ошибка

            dismiss()
        }
    }

    fun setListener(listener: NewItemListener) {
        this.listener = listener
    }
}