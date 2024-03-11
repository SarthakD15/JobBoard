package com.jobapplication.example.jobapplication.email

data class EmailDetails(
        val recipient: String,
        val subject: String,
        val msgBody: String
)
