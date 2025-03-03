package com.example.shop

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.shop.tools.NewItemDialogFragment

class MainActivity : AppCompatActivity(), ShopListFragment.ShowDialog {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addItem = findViewById<Button>(R.id.addItemBtn)
        addItem.setOnClickListener { }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, ShopListFragment.newInstance())
            .commit()
    }

    /**
     * Просто показывает [NewItemDialogFragment]
     */
    override fun showDialogFragment(dialogFragment: NewItemDialogFragment){
        dialogFragment.show(supportFragmentManager, "MyDialogFragment")
    }

}