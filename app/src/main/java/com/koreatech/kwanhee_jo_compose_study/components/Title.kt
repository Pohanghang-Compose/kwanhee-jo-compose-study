package com.koreatech.kwanhee_jo_compose_study.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.koreatech.kwanhee_jo_compose_study.ui.theme.Cyan400
import com.koreatech.kwanhee_jo_compose_study.ui.theme.Cyan600

@Composable
fun Title(titleText: String, subText: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = titleText,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Cyan600,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = subText,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Cyan400,
            modifier = Modifier.align(Alignment.Bottom)
        )
    }
}

@Preview
@Composable
fun TitlePreview() {
    Title("제목", "with 관희")
}