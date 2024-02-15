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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
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
        val defaultStudentID = getId()
        mutableStateOf(TextFieldValue(text = defaultStudentID))
    }
    val store = UserStore(context)

    Column(
        modifier = Modifier.clickable { keyboardController?.hide() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "DataStorage Assignment 1", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(15.dp))

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

        Button(
            onClick = {
//                CoroutineScope(Dispatchers.IO).launch {
//                    store.clearData()
//                }
            }
        ) {
            Text(text = "Hello ")
        }

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
                        val loadedUserName = store.getUserName.first()
                        val loadedEmail = store.getEmail.first()
                        val loadedStudentId = store.getStudentId.first()


                        usernameValue.value = TextFieldValue(loadedUserName)
                        emailValue.value = TextFieldValue(loadedEmail)
                        studentIdValue.value = TextFieldValue(loadedStudentId)
                    }
                }
            ) {
                Text(text = "Load")
            }
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveUser(usernameValue.value.text)
                        store.saveEmail(emailValue.value.text)
                        store.saveStudentId(studentIdValue.value.text)
                    }
                }
            ) {
                Text(text = "Save ")
            }
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        store.clearData()
                    }
                }
            ) {
                Text(text = "Clear ")
            }

        }

        Spacer(modifier = Modifier.height(100.dp))

        Card(
            modifier = Modifier
                .width(300.dp)
                .heightIn(min = 56.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = "Aasish Mahato", fontWeight = FontWeight.Bold)
                Text(text = "301373719", fontWeight = FontWeight.Bold)
            }
        }

    }

}

private fun getId(): String{
    val studentID = "301373719"
    return studentID.takeLast(3)
}


