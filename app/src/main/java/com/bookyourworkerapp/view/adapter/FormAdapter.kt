package com.bookyourworkerapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bookyourworkerapp.R
import com.bookyourworkerapp.database.FormEntity
import com.bookyourworkerapp.databinding.ListItemFormBinding

class FormAdapter: RecyclerView.Adapter<FormAdapter.ViewHolder>(){

    private var formList : List<FormEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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

    }

    fun setFormList(formList: List<FormEntity>) {
        this.formList = formList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding:ListItemFormBinding) : RecyclerView.ViewHolder(binding.root) {
        var textViewTitle: TextView = itemView.findViewById(R.id.tv_title_form)
        var textViewDate: TextView = itemView.findViewById(R.id.tv_date)
        var textViewRate: TextView = itemView.findViewById(R.id.tv_rate)
        var textViewJob: TextView = itemView.findViewById(R.id.tv_job)

    }

}