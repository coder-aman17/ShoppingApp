package com.example.userapp.presentation



import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.userapp.R
import com.example.userapp.presentation.Utils.CustomTextField


@Preview(showSystemUi = true)
@Composable
fun LoginScreen() {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color(0xFFF8F9FA)), // Light gray background
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Login",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E3A8A), // Professional blue color
            modifier = Modifier.padding(bottom = 8.dp)
        )

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            leadingIcon = Icons.Default.Email,
            modifier = Modifier.fillMaxWidth()
        )

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { /* Forgot Password Logic */ }) {
                Text(text = "Forgot Password?", color = Color(0xFF1E3A8A))
            }
        }

        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E3A8A)), // Professional blue
            elevation = ButtonDefaults.elevation(6.dp)
        ) {
            Text(
                text = "Login",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Already have an account?", color = Color.Gray)

            TextButton(onClick = { }) {
                Text(text = "Log in", color = Color(0xFF1E3A8A), fontWeight = FontWeight.Bold)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(modifier = Modifier.weight(3f), color = Color.Gray, thickness = 1.dp)

            Text(text = "Or", modifier = Modifier.padding(horizontal = 8.dp), color = Color.Gray)

            Divider(modifier = Modifier.weight(3f), color = Color.Gray, thickness = 1.dp)
        }

        OutlinedButton(
            onClick = { /* Google Sign-In Logic */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.elevation(6.dp)
        ) {
            Image(
                painter = painterResource(id =R.drawable.google),
                contentDescription = "Google Logo",
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Login with Google",
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

