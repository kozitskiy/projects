package com.example.kozitskiy_com.network.models

data class DataEmailRequest(
    val action_message: String,
    val name: String,
    val lname: String,
    val email: String,
    val message: String
)