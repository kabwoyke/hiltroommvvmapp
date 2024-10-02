package com.example.practiceproject.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.practiceproject.R

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    @DrawableRes imageResource:Int,
    firstName:String,
    lastName:String,
    memberId:String
) {

    Column {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),

            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .clip(CircleShape)
            )
            Column(

            ) {
                Text(text = "$firstName $lastName" , fontWeight = FontWeight.Bold  )
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = memberId)
            }
        }

    }


}