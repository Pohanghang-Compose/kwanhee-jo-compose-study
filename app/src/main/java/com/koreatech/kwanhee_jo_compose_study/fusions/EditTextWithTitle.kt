package com.koreatech.kwanhee_jo_compose_study.fusions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.koreatech.kwanhee_jo_compose_study.ui.theme.Cyan500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextWithTitle(
    titleText: String,
    labelText: String,
    placeholderText: String,
    leadingIcon: ImageVector,
    text: String,
    focusManager: FocusManager,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyBoardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = titleText,
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold,
            color = Cyan500,
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            leadingIcon = {
                Icon(imageVector = leadingIcon, contentDescription = null)
            },
            label = { Text(text = labelText) },
            placeholder = { Text(text = placeholderText) },
            maxLines = 1,
            visualTransformation = visualTransformation,
            keyboardOptions = keyBoardOptions,
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }, onDone =  {
                focusManager.clearFocus()
            }),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Cyan500,
                cursorColor = Cyan500,
                focusedLabelColor = Cyan500
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditTextWithTitlePreview() {
    var name by remember {
        mutableStateOf("")
    }

    EditTextWithTitle(
        "아이디",
        "ID",
        "Enter your id",
        Icons.Default.AccountBox,
        name,
        focusManager = LocalFocusManager.current,
        onTextChanged = { name = it },
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    )
}