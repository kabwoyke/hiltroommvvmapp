package com.example.practiceproject.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practiceproject.model.Member

@Database([Member::class] , version = 2)
abstract class MembersDatabase : RoomDatabase(){

    abstract fun getMemberDao() : MemberDao
}