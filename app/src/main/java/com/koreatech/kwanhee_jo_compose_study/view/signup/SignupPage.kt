package com.koreatech.kwanhee_jo_compose_study.view.signup

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.kwanhee_jo_compose_study.R
import com.koreatech.kwanhee_jo_compose_study.components.ButtonWithCornerShape
import com.koreatech.kwanhee_jo_compose_study.components.Title
import com.koreatech.kwanhee_jo_compose_study.fusions.EditTextWithTitle

@Composable
fun SignupPage(
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    onClickSignupButton: (id: String, password: String, nickname: String) -> Unit,
) {
    var id by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var nickname by rememberSaveable {
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
            focusManager = focusManager,
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
            focusManager = focusManager,
            onTextChanged = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
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
            focusManager = focusManager,
            keyBoardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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
            onClick = { onClickSignupButton(id, password, nickname) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPagePreview() {
//    SignUpPage(SignUpState.Loading) { id, password, nickname ->
//    }
}

