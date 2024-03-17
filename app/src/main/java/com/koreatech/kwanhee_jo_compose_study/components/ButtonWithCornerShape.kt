package com.koreatech.kwanhee_jo_compose_study.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.kwanhee_jo_compose_study.R
import com.koreatech.kwanhee_jo_compose_study.ui.theme.Cyan100
import com.koreatech.kwanhee_jo_compose_study.ui.theme.Cyan500

@Composable
fun ButtonWithCornerShape(
    contentText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Cyan500
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
        modifier = modifier
    ) {
        Text(
            text = contentText,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            color = Cyan100
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonWithCornerShapePreview() {
    ButtonWithCornerShape(
        contentText = stringResource(id = R.string.login),
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    ) {}
}