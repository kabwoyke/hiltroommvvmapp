package com.example.practiceproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceproject.model.Member
import com.example.practiceproject.repository.MembersRepository
import com.example.practiceproject.repository.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MembersViewModel @Inject constructor(
    private val membersRepository: MembersRepository
) : ViewModel() {

    // Flow to observe members from Room database
//    val r_members: Flow<List<Member>> = membersRepository.fetchAllMembers()

    // StateFlow to track the network response (loading, success, or error)
    private val _members = MutableStateFlow<NetworkResponse<List<Member>>>(NetworkResponse.Loading)
    val members: StateFlow<NetworkResponse<List<Member>>> = _members

    init {
        checkAndRefresh()  // Call this to initiate data fetching logic on ViewModel initialization
    }

    private fun checkAndRefresh() {
        viewModelScope.launch {
            try {
                _members.value = NetworkResponse.Loading  // Set Loading state

                // Check Room for existing members
                val roomMembers = membersRepository.fetchAllMembers().firstOrNull()

                if (roomMembers.isNullOrEmpty()) {
                    // If Room is empty, fetch from API
                    try {
                        val apiMembers = membersRepository.fetchAllMembersFromApi()  // Fetch members from API
                        _members.value = NetworkResponse.Success(apiMembers)  // Update with success if data is fetched
                    } catch (e: Exception) {
                        _members.value = NetworkResponse.Error(e.message ?: "Unknown error")  // Handle API error
                    }
                } else {
                    // If Room has data, set Success state with Room members
                    _members.value = NetworkResponse.Success(roomMembers)
                }
            } catch (e: Exception) {
                _members.value = NetworkResponse.Error("Failed to load data: ${e.message}")
            }
        }
    }

      fun refreshMembers(){
        viewModelScope.launch {
            try {
                _members.value = NetworkResponse.Loading

                val apimembers = membersRepository.fetchAllMembersFromApi()
                    _members.value = NetworkResponse.Success(apimembers)

            }catch (e:Exception){
                _members.value = NetworkResponse.Error(e.localizedMessage)
            }

        }
    }
}
