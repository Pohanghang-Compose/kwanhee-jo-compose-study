package com.koreatech.kwanhee_jo_compose_study.fusions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.kwanhee_jo_compose_study.R
import com.koreatech.kwanhee_jo_compose_study.data.ProfileMessageData

@Composable
fun HomeStateMessageBox(
    profileMessageState: ProfileMessageData,
    modifier: Modifier = Modifier,
) {
    if (profileMessageState.mode != null) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(20.dp),
                    color = when (profileMessageState.mode) {
                        1 -> Color.Black
                        2 -> Color.Red
                        3 -> Color.Green
                        else -> Color.Black
                    }
                )
                .padding(horizontal = 10.dp, vertical = 2.dp)

        ) {
            Text(
                text = when (profileMessageState.mode) {
                    1 -> stringResource(id = R.string.home_status_message)
                    2 -> stringResource(id = R.string.home_gift_message)
                    3 -> profileMessageState.messageContent ?: ""
                    else -> ""
                },
                color = Color.Black
            )

            Spacer(modifier = Modifier.width(2.dp))

            Image(
                imageVector = when (profileMessageState.mode) {
                    1 -> Icons.Default.Add
                    2 -> Icons.Default.Star
                    3 -> Icons.Default.PlayArrow
                    else -> Icons.Default.Close
                },
                contentDescription = null
            )

        }
    }
}

@Preview
@Composable
fun StateMessageBoxPreview() {
    HomeStateMessageBox(
        ProfileMessageData(1)
    )
}


@Preview
@Composable
fun StateMessageBoxPreview2() {
    HomeStateMessageBox(
        ProfileMessageData(2)
    )
}


@Preview
@Composable
fun StateMessageBoxPreview3() {
    HomeStateMessageBox(
        ProfileMessageData(3, "비행기 - 거북이")
    )
}
