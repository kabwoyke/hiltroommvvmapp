package com.example.practiceproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("members")
data class Member(
    @PrimaryKey
    val id:Int,
    val first_name:String,
    val last_name:String,
    val phone_number:String,
    val member_number:String,
    val id_number:String,
    val status:String,
    val gender: String,
    val total_missed_donations:Int
)