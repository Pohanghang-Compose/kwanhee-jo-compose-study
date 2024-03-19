package com.koreatech.kwanhee_jo_compose_study.fusions

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.koreatech.kwanhee_jo_compose_study.R
import com.koreatech.kwanhee_jo_compose_study.data.HomeProfileData
import com.koreatech.kwanhee_jo_compose_study.data.ProfileMessageData

@Composable
fun HomePersonProfile(
    context: Context,
    homeProfileData: HomeProfileData,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(end = 10.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(homeProfileData.profileUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_question_black),
                error = painterResource(id = R.drawable.ic_error),
                modifier = Modifier
                    .size(60.dp)
                    .padding(5.dp)
                    .clip(shape = RoundedCornerShape(40.dp)),

                )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = homeProfileData.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                HomeStateMessageBox(profileMessageState = homeProfileData.profileMessageData)
            }
        }
    }
}

@Preview
@Composable
fun HomePersonProfilePreview() {
    HomePersonProfile(
        LocalContext.current,
        HomeProfileData(
            profileMessageData = ProfileMessageData(),
            name = "조관희",
            profileUrl = "http://www.serebii.net/pokemongo/pokemon/001.png"
        )
    )
}

@Preview
@Composable
fun HomePersonProfilePreview2() {
    HomePersonProfile(
        LocalContext.current,
        HomeProfileData(
            profileMessageData = ProfileMessageData(2),
            name = "조관희",
            profileUrl = "http://www.serebii.net/pokemongo/pokemon/001.png"
        )
    )
}

@Preview
@Composable
fun HomePersonProfilePreview3() {
    HomePersonProfile(
        LocalContext.current,
        HomeProfileData(
            profileMessageData = ProfileMessageData(3, "비행기-거북이"),
            name = "조관희",
            profileUrl = "http://www.serebii.net/pokemongo/pokemon/001.png"
        )
    )
}