package com.pierbezuhoff.string_resource_test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.getString
import stringresourcetest.composeapp.generated.resources.Res
import stringresourcetest.composeapp.generated.resources.resource1
import stringresourcetest.composeapp.generated.resources.resource2
import stringresourcetest.composeapp.generated.resources.resource3

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun App() {
    MaterialTheme {
        var text by remember { mutableStateOf("loading...") }
        var launchGetString1 by remember { mutableStateOf(false) }
        var launchGetString2 by remember { mutableStateOf(false) }
        var launchGetString3 by remember { mutableStateOf(false) }
        Column {
            Text(text)
            Button(onClick = { launchGetString1 = true }) {
                Text("Launch getString")
            }
            Button(onClick = { launchGetString2 = true }) {
                Text("Launch getString @ Dispatchers.Main")
            }
            Button(onClick = { launchGetString3 = true }) {
                Text("Launch getString @ GlobalScope")
            }
        }
        LaunchedEffect(launchGetString1) {
            if (launchGetString1) {
                text = getString(Res.string.resource1)
                launchGetString1 = false
            }
        }
        LaunchedEffect(launchGetString2) {
            if (launchGetString2) {
                withContext(Dispatchers.Main) {
                    text = getString(Res.string.resource2)
                    launchGetString2 = false
                }
            }
        }
        LaunchedEffect(launchGetString3) {
            if (launchGetString3) {
                GlobalScope.launch {
                    text = getString(Res.string.resource3)
                    launchGetString3 = false
                }
            }
        }
    }
}