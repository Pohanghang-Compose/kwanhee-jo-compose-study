package com.koreatech.kwanhee_jo_compose_study.view.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    onClickLoginButton: (id: String, password: String) -> Unit,
    onClickSignupButton: () -> Unit,
) {
    var id by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        modifier = modifier.fillMaxHeight()
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Title(
                titleText = stringResource(id = R.string.login),
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
                onTextChanged = { changedId ->
                    id = changedId
                },
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
                keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.2f))

            ButtonWithCornerShape(
                contentText = stringResource(id = R.string.login),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                onClick = { onClickLoginButton(id, password) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            ButtonWithCornerShape(
                contentText = stringResource(id = R.string.sign_up),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                onClick = onClickSignupButton
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
//    LoginPage({}) {id, password ->
//
//    }
}
