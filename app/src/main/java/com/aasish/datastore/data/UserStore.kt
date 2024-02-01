package com.aasish.datastore.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
        private val USER_TOKEN_KEY = stringPreferencesKey("user_token")

        private val Context.dataStudent: DataStore<Preferences> by preferencesDataStore("student_id")
        private val STUDENT_ID_KEY = stringPreferencesKey("student_id")
    }

    val getAccessToken: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN_KEY] ?: ""
    }
    val getStudentId: Flow<String> = context.dataStudent.data.map { preferences ->
        preferences[STUDENT_ID_KEY] ?: ""
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }
    suspend fun saveStudentId(studentId: String) {
        context.dataStudent.edit { preferences ->
            preferences[STUDENT_ID_KEY] = studentId
        }
    }
}