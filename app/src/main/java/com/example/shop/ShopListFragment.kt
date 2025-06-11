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

class ShopListFragment : Fragment(), NewItemDialogFragment.NewItemListener, RecyclerAdapter.NotifyFragment {

    /**
     * Создаём интерфейс и через него используем функцию [showDialogFragment],
     * которая находиться в [MainActivity]
     */
    lateinit var items: ArrayList<ShopItem>
    private var listener: ShowDialog? = null
    private lateinit var serializer: JSONSerializer
    private lateinit var fragmentBinding: FragmentShopListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private var index: Int? = null

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
        index = items.size

        for((count, item) in items.withIndex()){
            item.setIndex(count)
        }

        recyclerView = fragmentBinding.recyclerView
        recyclerAdapter = RecyclerAdapter(items, this)
        with(recyclerView) {
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
        notifyFragment(items)
        super.onPause()
    }

    override fun onStop() {
        notifyFragment(items)
        super.onStop()
    }

    private fun showDialogFragment(dialogFragment: NewItemDialogFragment) {
        listener?.showDialogFragment(dialogFragment)
    }

    private fun createNewItem() {
        val dialogFragment = NewItemDialogFragment()

        dialogFragment.setListener(this)
        dialogFragment.show(requireActivity().supportFragmentManager, "NewItemDialogFragment")
    }

    override fun onSubmit(name: String) {
        if (name.replace(" ", "") != "") {
            items.add(ShopItem().also {
                it.setName(name)

                Log.d("TEST", "Added... ${it.getName()} to items")
            })
            recyclerAdapter.itemAdded(items)
            notifyFragment(items)
        }
    }
    override fun notifyFragment(items: ArrayList<ShopItem>){
        this.items = items
        Log.d("TEST","ITEMS: ${items.toString()}")
        for((count, item) in items.withIndex()){
            item.setIndex(count)
        }
        serializer.saveItems(items)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ShopListFragment()
    }


}