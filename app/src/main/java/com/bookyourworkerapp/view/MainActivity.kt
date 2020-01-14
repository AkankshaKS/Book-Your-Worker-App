package com.bookyourworkerapp.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookyourworkerapp.R
import com.bookyourworkerapp.database.FormEntity
import com.bookyourworkerapp.databinding.ActivityMainBinding
import com.bookyourworkerapp.view.adapter.FormAdapter
import com.bookyourworkerapp.viewmodel.FormViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.bottom_sheet.view.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding
    private val viewModel: FormViewModel by inject()
    private val adapter : FormAdapter by inject()
    lateinit var inflater : LayoutInflater


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.executePendingBindings()
        setupRecyclerView()
        setupButtonAddForm()


        viewModel.getAllForms().observe(this,
            Observer<List<FormEntity>> { list ->
                list?.let {
                    adapter.setFormList(it)
                }
            })



        inflater = LayoutInflater.from(applicationContext)
        val view = inflater.inflate(R.layout.bottom_sheet, null)
        view.bottom_delete.setOnClickListener(View.OnClickListener {
            deleteForm()
        })

    }


    private fun setupButtonAddForm() {

        addnewform.setOnClickListener(View.OnClickListener {
            startActivityForResult(
                Intent(this, NewFormActivity::class.java),
                ADD_NOTE_REQUEST)

        })
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val newForm = FormEntity(
                data.getStringExtra(NewFormActivity.EXTRA_TITLE),
                data.getStringExtra(NewFormActivity.EXTRA_DESCRIPTION),
                data.getStringExtra(NewFormActivity.EXTRA_BUDGET),
                data.getStringExtra(NewFormActivity.EXTRA_RATE),
                data.getStringExtra(NewFormActivity.EXTRA_PAYMENT_METHOD),
                data.getStringExtra(NewFormActivity.EXTRA_DATE),
                data.getStringExtra(NewFormActivity.EXTRA_JOBTERM))

            viewModel.insert(newForm)

            Toast.makeText(this, "Form saved!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        private const val ADD_NOTE_REQUEST = 1
    }



    private fun deleteForm() {
        viewModel.deleteAll()
    }

}
