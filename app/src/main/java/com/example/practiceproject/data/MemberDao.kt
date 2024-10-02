package com.example.practiceproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practiceproject.model.Member
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {

    @Query("SELECT * FROM members")
    fun fetchTodos() : Flow<List<Member>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(member: Member)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(members:List<Member>)

    @Query("DELETE FROM members")
    suspend fun clearAll()


}