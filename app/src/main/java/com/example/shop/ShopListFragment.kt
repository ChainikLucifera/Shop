package com.example.shop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shop.tools.NewItemDialogFragment

class ShopListFragment : Fragment() {

    /**
     * Создаём интерфейс и через него используем функцию [showDialogFragment],
     * которая находиться в [MainActivity]
     */
    private var listener: ShowDialog? = null

    interface ShowDialog { // при создании интерфейса, надо его использовать в активности, прописав
                           // в основании класса "..., ShopListFragment.ShowDialog"
        fun showDialogFragment(dialogFragment: NewItemDialogFragment)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          val view = inflater.inflate(R.layout.fragment_shop_list, container, false)

        val dialogFragment = NewItemDialogFragment()

        onAttach(requireContext())
        showDialogFragment(dialogFragment)


        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ShowDialog
        if (listener == null) {
            throw RuntimeException("$context must implement ShowDialog")
        }
    }

    private fun showDialogFragment(dialogFragment: NewItemDialogFragment) {
        listener?.showDialogFragment(dialogFragment)
    }

    companion object {

        @JvmStatic
        fun newInstance() = ShopListFragment()
    }
}