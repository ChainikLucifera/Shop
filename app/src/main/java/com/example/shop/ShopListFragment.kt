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
import com.example.shop.tools.JSONSerializer
import com.example.shop.tools.NewItemDialogFragment

class ShopListFragment : Fragment(), NewItemDialogFragment.NewItemListener {

    /**
     * Создаём интерфейс и через него используем функцию [showDialogFragment],
     * которая находиться в [MainActivity]
     */
    private var listener: ShowDialog? = null
    private lateinit var serializer: JSONSerializer
    private lateinit var items: ArrayList<ShopItem>
    private lateinit var fragmentBinding: FragmentShopListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter

    interface ShowDialog { // при создании интерфейса, надо его использовать в активности, прописав
                           // в основании класса "..., ShopListFragment.ShowDialog"
        fun showDialogFragment(dialogFragment: NewItemDialogFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //val view = inflater.inflate(R.layout.fragment_shop_list, container, false)


        //вместо view создал binding, так проще



        fragmentBinding = FragmentShopListBinding.inflate(inflater, container, false)
        serializer = JSONSerializer(requireContext(), Constants.SAVE_FILE_NAME)

        items = serializer.loadItems()

        recyclerView = fragmentBinding.recyclerView
        recyclerAdapter = RecyclerAdapter(items)
        with(recyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerAdapter
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

    override fun onPause() {
        serializer.saveItems(items)
        super.onPause()
    }

    private fun showDialogFragment(dialogFragment: NewItemDialogFragment) {
        listener?.showDialogFragment(dialogFragment)
    }

    private fun createNewItem(){
        val dialogFragment = NewItemDialogFragment()

        dialogFragment.setListener(this)
        dialogFragment.show(requireActivity().supportFragmentManager,"NewItemDialogFragment")
    }

    override fun onSubmit(name: String) {
        items.add(ShopItem().also {
            it.setName(name)
            Log.d("TEST","Added... ${it.getName()} to items")
        })
        recyclerAdapter.itemAdded(items)
        serializer.saveItems(items) // можно будет поменять сохрание после каждого элемента на сохранение в конце прилы
    }

    companion object {

        @JvmStatic
        fun newInstance() = ShopListFragment()
    }


}