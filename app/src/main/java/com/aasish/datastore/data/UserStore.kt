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
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("username")
        private val USER_name_KEY = stringPreferencesKey("user_name")

        private val Context.dataEmail: DataStore<Preferences> by preferencesDataStore("email")
        private val Email_KEY = stringPreferencesKey("email")

        private val Context.dataStudent: DataStore<Preferences> by preferencesDataStore("student_id")
        private val STUDENT_ID_KEY = stringPreferencesKey("student_id")
    }

    val getUserName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_name_KEY] ?: ""
    }
    val getEmail: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[Email_KEY] ?: ""
    }
    val getStudentId: Flow<String> = context.dataStudent.data.map { preferences ->
        preferences[STUDENT_ID_KEY] ?: ""
    }

    suspend fun saveToken(username: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_name_KEY] = username
        }
    }
    suspend fun saveEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[Email_KEY] = email
        }
    }
    suspend fun saveStudentId(studentId: String) {
        context.dataStudent.edit { preferences ->
            preferences[STUDENT_ID_KEY] = studentId
        }
    }
}