package com.koreatech.kwanhee_jo_compose_study.view.home

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.kwanhee_jo_compose_study.fusions.HomeMainProfile
import com.koreatech.kwanhee_jo_compose_study.fusions.HomePersonProfile
import com.koreatech.kwanhee_jo_compose_study.sample.homeProfileSampleData

@Composable
fun HomePage(
    context: Context,
    id: String,
    password: String,
    nickname: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Text(text = "id : $id")
        Text(text = "password : $password")
        Text(text = "nickname : $nickname")
        LazyColumn() {
            items(homeProfileSampleData.size) { index ->
                if (index == 0) {
                    HomeMainProfile(
                        context = context,
                        homeProfileData = homeProfileSampleData[index]
                    )
                } else {
                    HomePersonProfile(
                        context = context,
                        homeProfileData = homeProfileSampleData[index]
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePagePreview() {
    HomePage(context = LocalContext.current, "1", "2", "3")
}
