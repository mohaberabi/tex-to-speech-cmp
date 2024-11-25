package com.mohaberabi.kmp.texttospeech

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import texttospeech.composeapp.generated.resources.Res
import texttospeech.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        TextToSpeechScreen()
    }
}


@Composable
fun TextToSpeechScreen() {
    val tts = rememberTts()
    var text by remember { mutableStateOf("Hello, world!") }
    var selectedLanguage by remember { mutableStateOf(TtsLanguage.English) }
    var selectedVoice by remember { mutableStateOf(TtsVoice.Female) }



    Scaffold { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter Text") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Selector(
                selected = selectedLanguage,
                options = TtsLanguage.entries,
                label = { it.label }) {
                selectedLanguage = it
                tts.setLanguage(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Selector(selectedVoice, TtsVoice.entries, label = { it.name }) {
                selectedVoice = it
                tts.setVoice(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { tts.speech(text) }) {
                Text("Speak")
            }
        }
    }
}

@Composable
fun <T> Selector(
    selected: T,
    options: List<T>,
    label: (T) -> String,
    onOptionSelected: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Button(onClick = { expanded = true }) {
            Text(label(selected))
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(text = {
                    Text(label(option))
                }, onClick = {
                    onOptionSelected(option)
                    expanded = false
                })
            }
        }
    }
}