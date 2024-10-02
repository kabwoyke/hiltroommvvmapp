package com.example.practiceproject.di

import android.content.Context
import androidx.room.Room
import com.example.practiceproject.data.MemberApi
import com.example.practiceproject.data.MemberDao
import com.example.practiceproject.data.MembersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideMembersDao (membersDatabase: MembersDatabase) : MemberDao = membersDatabase.getMemberDao()

    @Singleton
    @Provides
    fun provideMemberDatabase (@ApplicationContext context: Context) : MembersDatabase = Room
                                                    .databaseBuilder(
                                                        context,
                                                        MembersDatabase::class.java,
                                                        "membersDb"
                                                    ).fallbackToDestructiveMigration().build()
    @Singleton
    @Provides
    fun provideMemberApi () : MemberApi = Retrofit.Builder()
                                .baseUrl("http://192.168.100.3:8000/api/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
        .create(MemberApi::class.java)

}