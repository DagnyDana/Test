package com.example.counterapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.counterapplication.ui.theme.CounterApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterApplicationTheme {
                WordCounterApp()
            }
        }
    }
}

@Composable
fun WordCounterApp() {
    val context = LocalContext.current
    var inputText by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("Words") }
    var resultText by remember { mutableStateOf("") }
    val options = listOf("Words", "Characters")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = inputText,
            onValueChange = { inputText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if (inputText.isEmpty()) {
                        Text("Enter text")
                    }
                    innerTextField()
                }
            }
        )

        var expanded by remember { mutableStateOf(false) }
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { expanded = true }) {
                Text(text = selectedOption)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (inputText.isEmpty()) {
                    Toast.makeText(context, "Please enter some text", Toast.LENGTH_SHORT).show()
                } else {
                    resultText = if (selectedOption == "Words") {
                        "Word Count: ${TextCounter.countWords(inputText)}"
                    } else {
                        "Character Count: ${TextCounter.countCharacters(inputText)}"
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Count")
        }

        Text(text = resultText, modifier = Modifier.padding(top = 16.dp))
    }
}

class TextCounter {
    companion object {
        fun countWords(text: String): Int {
            return text.split(Regex("[\\s,.]+")).filter { it.isNotEmpty() }.size
        }

        fun countCharacters(text: String): Int {
            return text.length
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordCounterAppPreview() {
    CounterApplicationTheme {
        WordCounterApp()
    }
}
