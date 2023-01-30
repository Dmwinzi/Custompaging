package com.example.custompaged

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.custompaged.Data.Remote.Api
import com.example.custompaged.Data.Repository.Repository
import com.example.custompaged.ui.theme.CustompagedTheme
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api  = Api.invoke()
        val repository = Repository(api)
        var factory  = viewmodelFctory(repository)
        val viewModel by viewModels<Mainviewmodel> {factory}

        setContent {
            CustompagedTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Users(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Users(viewModel: Mainviewmodel) {

    var isLoading  by remember { mutableStateOf(false)}
    var context  = LocalContext.current
    var users = viewModel.user.collectAsLazyPagingItems()

    for (user in users.itemSnapshotList){
        Log.d("Users", user.toString())
    }

    when(users.loadState.append){
        is LoadState.NotLoading ->  isLoading
        is LoadState.Error -> {isLoading
            Toast.makeText(context,"Error fetching data",Toast.LENGTH_LONG).show()
        }
        LoadState.Loading  -> isLoading = true
    }

    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(users){ item -> 
            Column(modifier = Modifier.fillMaxSize()) {
                
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text(text = item!!.username, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                        Text(text = item!!.email, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                    }
                }
                
            }
            
            
        }

        item {
            Dialog(isLoading = isLoading)
        }

    }
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustompagedTheme {

    }
}