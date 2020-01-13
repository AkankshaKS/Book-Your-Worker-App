package com.bookyourworkerapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.bookyourworkerapp.R
import com.bookyourworkerapp.databinding.ActivityMainBinding
import com.bookyourworkerapp.model.FormData

public class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
       // val details = FormData()
       // mainBinding.setVariable(BR.details, details)
        mainBinding.executePendingBindings()

        mainBinding.addnewform.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                val intent = Intent(this@MainActivity, NewFormActivity::class.java)
                startActivity(intent)
            }

        })

    }


}
