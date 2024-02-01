package com.aasish.datastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aasish.datastore.data.UserStore
import com.aasish.datastore.ui.theme.DataStoreTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun Main() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val usernameValue = remember {
        mutableStateOf(TextFieldValue())
    }
    val emailValue = remember {
        mutableStateOf(TextFieldValue())
    }

    val studentIdValue = remember {
        mutableStateOf(TextFieldValue())
    }
    val store = UserStore(context)
    val usernameText = store.getAccessToken.collectAsState(initial = "")
    val emailText = store.getStudentId.collectAsState(initial = "")
    val studentidText = store.getStudentId.collectAsState(initial = "")

    Column(
        modifier = Modifier.clickable { keyboardController?.hide() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "DataStorage Assignment 1", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = usernameText.value)
        Text(text = emailText.value)
        Text(text = studentidText.value )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            label = {Text("Username")},
            value = usernameValue.value,
            onValueChange = { usernameValue.value = it },
        )
//        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            label = {Text("Email")},
            value = emailValue.value,
            onValueChange = { emailValue.value = it },
        )

        OutlinedTextField(
            label = {Text("ID")},
            value = studentIdValue.value,
            onValueChange = { studentIdValue.value = it },
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Button(

                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveToken(usernameValue.value.text)
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveToken(emailValue.value.text)
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveStudentId(studentIdValue.value.text)
                    }
                }
            ) {
                Text(text = "Load")
            }
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveToken(usernameValue.value.text)
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveToken(emailValue.value.text)
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveStudentId(studentIdValue.value.text)
                    }
                }
            ) {
                Text(text = "Save ")
            }
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveToken(usernameValue.value.text)
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveToken(emailValue.value.text)
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveStudentId(studentIdValue.value.text)
                    }
                }
            ) {
                Text(text = "Clear ")
            }

        }

    }
}