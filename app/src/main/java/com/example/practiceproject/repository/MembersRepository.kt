package com.example.practiceproject.repository

import androidx.lifecycle.ViewModel
import com.example.practiceproject.data.MemberApi
import com.example.practiceproject.data.MemberDao
import com.example.practiceproject.model.Member
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MembersRepository @Inject constructor(
    private val memberDao: MemberDao,
    private val memberApi: MemberApi
) {

    // Fetching members from Room database as Flow
    fun fetchAllMembers(): Flow<List<Member>> = memberDao.fetchTodos()

    // Fetch members from the API and save them into Room
    suspend fun fetchAllMembersFromApi(): List<Member> {
        return try {
            val response = memberApi.fetchMembers()

            if (response.isSuccessful) {
                response.body()?.let { members ->
                    if (members.isNotEmpty()) {
                        memberDao.clearAll()
                        memberDao.insertAll(members)  // Save the members into Room
                    }
                    return members  // Return the fetched members
                } ?: emptyList()  // Return an empty list if the body is null
            } else {
                throw Exception("API request failed with status code: ${response.code()}")
            }
        } catch (e: Exception) {
            // Log or handle the exception here as needed
            throw Exception("Failed to fetch members from API: ${e.message}", e)
        }
    }
}
