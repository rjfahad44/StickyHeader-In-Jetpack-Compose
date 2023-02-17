package com.example.stickyheaderinjetpackcompose

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stickyheaderinjetpackcompose.ui.theme.StickyHeaderInJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StickyHeaderInJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StickyHeaderDemo()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickyHeaderDemo() {
    val list = dataList().groupBy { it.age }

    LazyColumn {
        list.onEach { (age, demoList) ->

            stickyHeader {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = Color.DarkGray),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = age.toString(),
                        style = MaterialTheme.typography.h5,
                        color = Color.White
                    )
                }
            }

            items(demoList) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(text = "Name : ${it.name}\nAge : ${it.age}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp,0.dp, 0.dp, 0.dp))
                }
            }
        }
    }
}


fun dataList(): List<ListModel> {
    val demoList = arrayListOf<ListModel>()
    val name = "a"
    var age = 1
    var counter = 0
    for (i in 0..50) {
        if (counter == 5) {
            counter = 0
            age = i
        }
        counter++
        demoList.add(ListModel(name + i.toString(), age))
    }
    return demoList
}
