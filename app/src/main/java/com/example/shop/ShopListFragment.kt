package com.example.shop

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.databinding.FragmentShopListBinding
import com.example.shop.tools.NewItemDialogFragment

class ShopListFragment : Fragment() {

    /**
     * Создаём интерфейс и через него используем функцию [showDialogFragment],
     * которая находиться в [MainActivity]
     */
    private var listener: ShowDialog? = null
    private lateinit var items: ArrayList<ShopItem>
    private lateinit var fragmentBinding: FragmentShopListBinding

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
    ): View {

        //val view = inflater.inflate(R.layout.fragment_shop_list, container, false)


        //вместо view создал binding, так проще
        fragmentBinding = FragmentShopListBinding.inflate(inflater, container, false)

        items = ArrayList<ShopItem>().apply{
            add(ShopItem("Cringe1"))
            add(ShopItem("Cringe2"))
            add(ShopItem("Cringe3"))
        }

        with(fragmentBinding.recyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RecyclerAdapter(items)
        }

        fragmentBinding.addItemBtn.setOnClickListener {
            createNewItem()
        }

         
        return fragmentBinding.root
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

    private fun createNewItem(){
        val dialogFragment = NewItemDialogFragment()
        showDialogFragment(dialogFragment)
    }

    companion object {

        @JvmStatic
        fun newInstance() = ShopListFragment()
    }
}