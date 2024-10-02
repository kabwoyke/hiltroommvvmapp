package com.example.practiceproject.data

import com.example.practiceproject.model.Member
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface MemberApi {
    @GET("members")
    suspend fun fetchMembers () : Response<List<Member>>
}