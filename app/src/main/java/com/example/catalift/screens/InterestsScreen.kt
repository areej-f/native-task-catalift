package com.example.catalift.screens

import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InterestsScreen(
    onContinue: () -> Unit,
    onBack: () -> Unit
) {
    val interests = List(24) { "Lorem ipsum" }
    val selectedInterests = remember { mutableStateListOf<String>() }

    var showValidationError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Your Interests", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
//                mainAxisSpacing = 8.dp,
//                crossAxisSpacing = 8.dp
            ) {
                interests.forEach { interest ->
                    val isSelected = selectedInterests.contains(interest)
                    val toggleSelection = {
                        if (isSelected) selectedInterests.remove(interest)
                        else selectedInterests.add(interest)
                        showValidationError = false
                    }

                    Button(
                        onClick = toggleSelection,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Color(0xFF0A0E60) else Color.White,
                            contentColor = if (isSelected) Color.White else Color(0xFF0A0E60)
                        ),
                        border = BorderStroke(1.dp, Color(0xFF0A0E60)),
                        shape = RoundedCornerShape(50),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(interest)
                    }
                }
            }

            if (showValidationError && selectedInterests.isEmpty()) {
                Text(
                    "Please select at least one interest.",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Column {
            Button(
                onClick = {
                    if (selectedInterests.isEmpty()) {
                        showValidationError = true
                    } else {
                        showValidationError = false
                        onContinue()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Continue")
            }

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Back")
            }
        }
    }
}
