package com.bonin.feature.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonin.core.ui.BoninBrand
import com.bonin.core.ui.BoninPasswordField
import com.bonin.core.ui.BoninTextField
import com.bonin.ui.theme.BoninTheme

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onBackToLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }

    val passwordMismatch =
        confirmPassword.isNotEmpty() &&
            password != confirmPassword

    val canRegister =
        name.isNotBlank() &&
            email.isNotBlank() &&
            password.length >= 8 &&
            password == confirmPassword

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .imePadding()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height(40.dp)
        )

        BoninBrand()

        Spacer(
            modifier = Modifier.height(36.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Create your account",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Start tracking shared expenses without the awkward math.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BoninTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = "Name"
            )

            BoninTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = "Email",
                keyboardType = KeyboardType.Email
            )

            BoninPasswordField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = "Password",
                imeAction = androidx.compose.ui.text.input.ImeAction.Next
            )

            BoninPasswordField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                },
                label = "Confirm password",
                isError = passwordMismatch,
                supportingText = if (passwordMismatch) {
                    "Passwords do not match"
                } else {
                    null
                }
            )
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        Button(
            onClick = onRegisterClick,
            enabled = canRegister,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Create account",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        TextButton(
            onClick = onBackToLoginClick
        ) {
            Text(
                text = "Already have an account? Sign in",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(
            modifier = Modifier.height(32.dp)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun RegisterScreenPreview() {
    BoninTheme {
        RegisterScreen(
            onRegisterClick = {},
            onBackToLoginClick = {}
        )
    }
}
