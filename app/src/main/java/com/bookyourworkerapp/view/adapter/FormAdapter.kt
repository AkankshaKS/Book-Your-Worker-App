package com.bookyourworkerapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bookyourworkerapp.R
import com.bookyourworkerapp.database.FormEntity
import com.bookyourworkerapp.databinding.ListItemFormBinding
import com.bookyourworkerapp.repository.FormRepository
import com.bookyourworkerapp.viewmodel.FormViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet.view.*
import org.koin.android.ext.android.inject


class FormAdapter(): RecyclerView.Adapter<FormAdapter.ViewHolder>(){

    private var formList : List<FormEntity> = ArrayList()
    lateinit var inflater : LayoutInflater
    lateinit var dialog: BottomSheetDialog


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        inflater = LayoutInflater.from(parent.context)
        dialog = BottomSheetDialog(parent.context)
        val binding: ListItemFormBinding = DataBindingUtil.inflate(LayoutInflater.from
            (parent.context), R.layout.list_item_form, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = formList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentForm = formList[position]
        holder.textViewTitle.text = currentForm.title
        holder.textViewDate.text = currentForm.date
        holder.textViewRate.text = currentForm.rate
        holder.textViewJob.text = currentForm.jobTerm
        holder.menuDelete.setOnClickListener {

            val view = inflater.inflate(R.layout.bottom_sheet, null)
            dialog.setContentView(view)
            dialog.show()
        }

    }

    fun setFormList(formList: List<FormEntity>) {
        this.formList = formList
        notifyDataSetChanged()
    }

    class ViewHolder(binding:ListItemFormBinding) : RecyclerView.ViewHolder(binding.root) {
        var textViewTitle: TextView = itemView.findViewById(R.id.tv_title_form)
        var textViewDate: TextView = itemView.findViewById(R.id.tv_date)
        var textViewRate: TextView = itemView.findViewById(R.id.tv_rate)
        var textViewJob: TextView = itemView.findViewById(R.id.tv_job)
        var menuDelete : RelativeLayout = itemView.findViewById(R.id.deleteForm)

    }

}