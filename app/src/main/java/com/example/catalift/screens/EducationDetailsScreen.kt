package com.example.catalift.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catalift.DropdownSelector

@Composable
fun EducationDetailsScreen(
    onContinue: () -> Unit,
    onBack: () -> Unit
) {
    var highestEducation by remember { mutableStateOf("") }
    var institution by remember { mutableStateOf("") }
    var relevantRoles by remember { mutableStateOf("") }

    var showValidationError by remember { mutableStateOf(false) }

    val educationLevels = listOf("High School", "Bachelor's", "Master's", "Ph.D")
    val institutions = listOf("Institution A", "Institution B", "Institution C")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Your Education Details", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))

            DropdownSelector("Highest Education Level", highestEducation, educationLevels) {
                highestEducation = it
                showValidationError = false
            }

            if (showValidationError && highestEducation.isBlank()) {
                Text("Please select your highest education level.", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(Modifier.height(12.dp))

            DropdownSelector("Current Institution", institution, institutions) {
                institution = it
                showValidationError = false
            }

            if (showValidationError && institution.isBlank()) {
                Text("Please select your current institution.", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = relevantRoles,
                onValueChange = {
                    if (it.length <= 100) relevantRoles = it
                },
                label = { Text("Relevant Past Roles/Internships") },
                placeholder = { Text("Write in 100 words") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Text("${relevantRoles.length}/100 words typed", fontSize = 12.sp, modifier = Modifier.align(
                Alignment.End))

            if (showValidationError && relevantRoles.isBlank()) {
                Text("Please describe at least one role or internship.", color = Color.Red, fontSize = 12.sp)
            }
        }

        Column {
            Button(
                onClick = {
                    if (highestEducation.isBlank() || institution.isBlank() || relevantRoles.isBlank()) {
                        showValidationError = true
                    } else {
                        showValidationError = false
                        onContinue()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
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
