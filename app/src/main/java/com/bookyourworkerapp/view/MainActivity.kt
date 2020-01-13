package com.bookyourworkerapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookyourworkerapp.R
import com.bookyourworkerapp.databinding.ActivityMainBinding
import com.bookyourworkerapp.view.adapter.FormAdapter
import com.bookyourworkerapp.viewmodel.FormViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding
    private val viewModel: FormViewModel by inject()
    private val adapter : FormAdapter by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.executePendingBindings()
        setupRecyclerView()

        mainBinding.addnewform.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                val intent = Intent(this@MainActivity, NewFormActivity::class.java)
                startActivity(intent)
            }

        })
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }


}
