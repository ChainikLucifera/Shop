package com.example.shop

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.shop.databinding.ActivityMainBinding
import com.example.shop.tools.NewItemDialogFragment

class MainActivity : AppCompatActivity(), ShopListFragment.ShowDialog {

    public lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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