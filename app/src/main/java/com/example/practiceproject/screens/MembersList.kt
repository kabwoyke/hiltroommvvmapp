package com.example.practiceproject.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.practiceproject.R
import com.example.practiceproject.components.ListItem
import com.example.practiceproject.repository.NetworkResponse
import com.example.practiceproject.viewmodel.MembersViewModel

@Composable
fun MembersListPage(modifier: Modifier = Modifier, membersViewModel: MembersViewModel) {
    // Collect the state from the ViewModel
    val membersState = membersViewModel.members.collectAsState()

    // Handle different states of NetworkResponse (Loading, Success, Error)
    when (val result = membersState.value) {
        is NetworkResponse.Loading -> {
            // Show a loading indicator when data is being fetched
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is NetworkResponse.Error -> {
            // Show an error message if the API call or Room data fetch fails
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = result.message)
            }
        }
        is NetworkResponse.Success -> {
            // If data is successfully fetched from the API or Room, show the list of members
            LazyColumn(modifier = modifier.fillMaxSize()) {
                itemsIndexed(result.membersList) { index , member ->
                    // Display the member list items here
                    ListItem(
                        imageResource = if (member.gender == "FEMALE") R.drawable.female else R.drawable.male,
                        firstName = member.first_name,
                        lastName = member.last_name,
                        memberId = member.member_number
                    )
                }
            }
            Button(onClick = {
                membersViewModel.refreshMembers()
            }) {

                Text(text = "Refresh")

            }
        }
    }
}
