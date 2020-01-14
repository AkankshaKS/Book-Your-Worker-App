package com.bookyourworkerapp.view

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
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
    var cal = Calendar.getInstance()

    companion object {
        const val EXTRA_TITLE = "com.bookyourworkerapp.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.bookyourworkerapp.EXTRA_DESCRIPTION"
        const val EXTRA_BUDGET = "com.bookyourworkerapp.EXTRA_BUDGET"
        const val EXTRA_RATE = "com.bookyourworkerapp.EXTRA_RATE"
        const val EXTRA_PAYMENT_METHOD = "com.bookyourworkerapp.EXTRA_PAYMENT_METHOD"
        const val EXTRA_DATE = "com.bookyourworkerapp.EXTRA_DATE"
        const val EXTRA_JOBTERM = "com.bookyourworkerapp.EXTRA_JOBTERM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_form)
        binding.executePendingBindings()

        form_title.addTextChangedListener(textWatcher)
        form_desc.addTextChangedListener(textWatcher)
        form_budget.addTextChangedListener(textWatcher)


        save_form.setOnClickListener(this)
        form_date.setOnClickListener(this)
        form_rate.setOnClickListener(this)
        form_payment_method.setOnClickListener(this)
        form_job_term.setOnClickListener(this)
    }

    private fun saveForm() {

        if (form_title.text.toString().trim().isBlank() ||
            form_desc.text.toString().trim().isBlank() ||
            form_budget.text.toString().trim().isBlank() ||
            form_rate.text.toString().trim().isBlank() ||
            form_payment_method.text.toString().trim().isBlank() ||
            form_date.text.toString().trim().isBlank() ||
            form_job_term.text.toString().trim().isBlank() ) {
            Toast.makeText(this, "Can not insert empty note!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_TITLE, form_title.text.toString())
            putExtra(EXTRA_DESCRIPTION, form_desc.text.toString())
            putExtra(EXTRA_BUDGET, form_budget.text.toString())
            putExtra(EXTRA_RATE, form_rate.text.toString())
            putExtra(EXTRA_PAYMENT_METHOD, form_payment_method.text.toString())
            putExtra(EXTRA_DATE, form_date.text.toString())
            putExtra(EXTRA_JOBTERM, form_job_term.text.toString())
        }

        setResult(Activity.RESULT_OK, data)
        finish()

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
            form_rate -> showRateDialog()
            form_payment_method -> showPaymentDialog()
            form_job_term -> showJobTermDialog()

        }

    }

    private fun showJobTermDialog() {
        val listItems = arrayOf("No Preference", "Recurring Job", "Same Day Job", "Multi Days Job")
        val mAlertDialog = AlertDialog.Builder(this@NewFormActivity)
        mAlertDialog.setTitle("Job Term")

        mAlertDialog.setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
            form_job_term!!.setText(listItems[i])
            dialogInterface.dismiss()
        }

        val alert = mAlertDialog.create()
        alert.show()
    }

    private fun showPaymentDialog() {
        val listItems = arrayOf("No Preference", "E- Preference", "Cash")
        val mAlertDialog = AlertDialog.Builder(this@NewFormActivity)
        mAlertDialog.setTitle("Payment Method")

        mAlertDialog.setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
            form_payment_method!!.setText(listItems[i])
            dialogInterface.dismiss()
        }

        val alert = mAlertDialog.create()
        alert.show()
    }

    private fun showRateDialog() {

        val listItems = arrayOf("No Preference", "Fixed Budget", "HourlyRate")
        val mAlertDialog = AlertDialog.Builder(this@NewFormActivity)
        mAlertDialog.setTitle("Rate")

        mAlertDialog.setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
            form_rate!!.setText(listItems[i])
            dialogInterface.dismiss()
        }

        val alert = mAlertDialog.create()
        alert.show()
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
