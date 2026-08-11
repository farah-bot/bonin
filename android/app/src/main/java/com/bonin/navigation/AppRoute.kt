package com.bonin.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute : NavKey

@Serializable
data object RegisterRoute : NavKey

@Serializable
data object HomeRoute : NavKey