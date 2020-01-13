package com.bookyourworkerapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bookyourworkerapp.R
import com.bookyourworkerapp.database.FormEntity
import com.bookyourworkerapp.databinding.ListItemFormBinding

class FormAdapter: RecyclerView.Adapter<FormAdapter.ViewHolder>(){

    private val formList : List<FormEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemFormBinding = DataBindingUtil.inflate(LayoutInflater.from
            (parent.context), R.layout.list_item_form, parent, false)
        return ViewHolder(binding)
    }



    override fun getItemCount(): Int = formList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder
    }

    class ViewHolder(val binding:ListItemFormBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: FormEntity){

        }

    }

}