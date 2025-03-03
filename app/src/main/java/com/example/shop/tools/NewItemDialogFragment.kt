package com.example.shop.tools

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


/**
 * Отдельный [DialogFragment] для добавления продуктов
 */
class NewItemDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Заголовок диалога")
            .setMessage("Сообщение в диалоге")
            .setPositiveButton("OK") { _: DialogInterface, _: Int ->
                // Действие при нажатии на OK
            }
            .setNegativeButton("Отмена") { _: DialogInterface, _: Int ->
                // Действие при нажатии на Отмена
            }
            .create()
    }
}