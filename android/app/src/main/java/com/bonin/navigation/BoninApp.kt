package com.bonin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.bonin.feature.auth.presentation.LoginScreen
import com.bonin.feature.auth.presentation.RegisterScreen
import com.bonin.feature.home.presentation.HomeScreen

@Composable
fun BoninApp() {
    val backStack = rememberNavBackStack(LoginRoute)

    fun navigateToHome() {
        backStack.clear()
        backStack.add(HomeRoute)
    }

    fun navigateToLogin() {
        backStack.clear()
        backStack.add(LoginRoute)
    }

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            }
        },
        entryProvider = { key ->
            when (key) {
                LoginRoute -> NavEntry(key) {
                    LoginScreen(
                        onLoginClick = {
                            navigateToHome()
                        },
                        onRegisterClick = {
                            backStack.add(RegisterRoute)
                        }
                    )
                }

                RegisterRoute -> NavEntry(key) {
                    RegisterScreen(
                        onBackToLoginClick = {
                            backStack.removeLastOrNull()
                        },
                        onRegisterClick = {
                            navigateToHome()
                        }
                    )
                }

                HomeRoute -> NavEntry(key) {
                    HomeScreen(
                        userName = "Farah",
                        onLogoutClick = {
                            navigateToLogin()
                        }
                    )
                }

                else -> error("Unknown navigation key: $key")
            }
        }
    )
}