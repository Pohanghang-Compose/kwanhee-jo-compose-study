package com.koreatech.kwanhee_jo_compose_study.view.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.kwanhee_jo_compose_study.R
import com.koreatech.kwanhee_jo_compose_study.components.ButtonWithCornerShape
import com.koreatech.kwanhee_jo_compose_study.components.Title
import com.koreatech.kwanhee_jo_compose_study.fusions.EditTextWithTitle

@Composable
fun SignUpPage(
    onStateChange: (SignUpState) -> Unit,
    modifier: Modifier = Modifier,
//    onClickSignUp: (id: String, password: String, nickname: String) -> Unit,
) {
    var id by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var nickname by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier.fillMaxHeight()) {
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Title(
            titleText = stringResource(id = R.string.sign_up),
            subText = stringResource(id = R.string.with_me),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        EditTextWithTitle(
            titleText = stringResource(id = R.string.id),
            labelText = stringResource(id = R.string.id_upper),
            placeholderText = stringResource(id = R.string.id_input),
            leadingIcon = Icons.Default.AccountBox,
            text = id,
            onTextChanged = { id = it },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        EditTextWithTitle(
            titleText = stringResource(id = R.string.password),
            labelText = stringResource(id = R.string.password_upper),
            placeholderText = stringResource(id = R.string.password_input),
            leadingIcon = Icons.Default.Lock,
            text = password,
            onTextChanged = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        EditTextWithTitle(
            titleText = stringResource(id = R.string.nickname),
            labelText = stringResource(id = R.string.nickname_upper),
            placeholderText = stringResource(id = R.string.nickname_input),
            leadingIcon = Icons.Default.Face,
            text = nickname,
            onTextChanged = { nickname = it },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        )


        Spacer(modifier = Modifier.fillMaxHeight(0.3f))

        ButtonWithCornerShape(
            contentText = stringResource(id = R.string.sign_up),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            onClick = {
                if (!(id.length in 6..10)) onStateChange(SignUpState.ErrorId)
                else if (!(password.length in 8..10)) onStateChange(SignUpState.ErrorPassword)
                else if ((nickname.length <= 0)) onStateChange(SignUpState.ErrorNickname)
                else onStateChange(SignUpState.Success(id, password, nickname))
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPagePreview() {
//    SignUpPage(SignUpState.Loading) { id, password, nickname ->
//    }
}

