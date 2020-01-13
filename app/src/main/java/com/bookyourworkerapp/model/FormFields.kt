package com.bookyourworkerapp.model

data class FormData(val title: String, val description: String, val budget : Int,
                    val rate: String, val paymentmethod: String, val date: Int, val jobTerm: String,
                    val attachments: Int)