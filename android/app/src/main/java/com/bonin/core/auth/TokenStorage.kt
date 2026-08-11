package com.bonin.core.auth

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.authDataStore
        by preferencesDataStore(
            name = "auth"
        )

@Singleton
class TokenStorage @Inject constructor(
    @ApplicationContext
    context: Context
) {

    private val dataStore =
        context.authDataStore

    val accessToken: Flow<String?> =
        dataStore
            .data
            .map { preferences ->

                preferences[
                    ACCESS_TOKEN_KEY
                ]
            }

    suspend fun saveAccessToken(
        token: String
    ) {

        dataStore.edit { preferences ->

            preferences[
                ACCESS_TOKEN_KEY
            ] = token
        }
    }

    suspend fun clearAccessToken() {

        dataStore.edit { preferences ->

            preferences.remove(
                ACCESS_TOKEN_KEY
            )
        }
    }

    private companion object {

        val ACCESS_TOKEN_KEY =
            stringPreferencesKey(
                "access_token"
            )
    }
}