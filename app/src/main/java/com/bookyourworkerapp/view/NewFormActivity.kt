package com.bookyourworkerapp.view

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bookyourworkerapp.R
import com.bookyourworkerapp.database.FormEntity
import com.bookyourworkerapp.databinding.ActivityNewFormBinding
import com.bookyourworkerapp.viewmodel.FormViewModel
import kotlinx.android.synthetic.main.activity_new_form.*
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class NewFormActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityNewFormBinding
    private val viewModel: FormViewModel by inject()
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_form)
        binding.executePendingBindings()

        form_title.addTextChangedListener(textWatcher)
        form_desc.addTextChangedListener(textWatcher)
        form_budget.addTextChangedListener(textWatcher)

        save_form.setOnClickListener(this)
        form_date.setOnClickListener(this)
    }

    private fun saveForm() {

        val newForm = FormEntity(form_title.text.toString(), form_desc.text.toString(),
           form_budget.text.toString(), form_rate.text.toString(), form_payment_method.text.toString(),
            form_date.text.toString(), form_job_term.text.toString() )
        viewModel.insert(newForm)

    }

    val dateSetListener = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                               dayOfMonth: Int) {
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }
    }

    val textWatcher = object : TextWatcher {
        override
        fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {


        }

        override
        fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {


        }

        override
        fun afterTextChanged(editable: Editable?) {
            /*if (editable != null && !editable.toString().equals("")) {
                // Checking editable.hashCode() to understand which edittext is using right now
                if (editText.editText!!.text.hashCode() === editable.hashCode()) {
                    // This is just an example, your magic will be here!

                    val value = editable.toString()
                    editText.editText!!.removeTextChangedListener(this)
                    editText.editText!!.setText(value)
                    editText.editText!!.addTextChangedListener(this)
                }
            } else if (editText2.editText!!.text.hashCode() === editable!!.hashCode()) {
                // This is just an example, your magic will be here!

                val value = editable!!.toString()
                editText2.editText!!.removeTextChangedListener(this)
                editText2.editText!!.setText(value)
                editText2.editText!!.addTextChangedListener(this)
            }
        }*/
        }
    }

    override fun onClick(v: View?) {

        when(v){
            save_form -> saveForm()
            form_date -> pickDate()
            form_rate -> showDialog()

        }

    }

    private fun showDialog() {

        //AlertDialog.Builder alertDialog = new
    }

    private fun pickDate() {

        DatePickerDialog(this@NewFormActivity,
            dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        form_date!!.setText(sdf.format(cal.getTime()))
    }

}
