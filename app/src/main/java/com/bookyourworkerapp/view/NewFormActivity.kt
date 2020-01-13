package com.bookyourworkerapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bookyourworkerapp.R
import com.bookyourworkerapp.databinding.ActivityNewFormBinding

public class NewFormActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_form)
        binding.executePendingBindings()
        
    }


}