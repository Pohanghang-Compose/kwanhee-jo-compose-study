package com.koreatech.kwanhee_jo_compose_study.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.koreatech.kwanhee_jo_compose_study.ui.theme.Cyan500

@Composable
fun HomePage(
    id: String,
    password: String,
    nickname: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        Text(
            text = "ID : $id",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Cyan500
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Password : $password",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Cyan500
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "nickname : $nickname",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Cyan500
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview
@Composable
fun HomePagePreview() {
    HomePage("id", "password", "nickname")
}