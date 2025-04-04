package com.example.userapp.presentation.Utils

import android.graphics.drawable.Icon
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.userapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessAlertDialog(
    onClick : () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = {},
        modifier = Modifier.background(shape = RoundedCornerShape(16.dp), color = Color.White),
        content = {
            Column (
                modifier = Modifier.fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box(modifier = Modifier.size(64.dp)
                    .background(color = colorResource(id = R.color.purple_200), shape = CircleShape)){
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Success",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp))

                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Success",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Congratulations! Your have \n completed your Registration.",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = onClick,
                    modifier = Modifier.fillMaxWidth()
                        .padding(48.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.teal_700)),
                    shape = RoundedCornerShape(8.dp)
                    ) {

                    Text(text = "Go to Home",
                        color = Color.Gray,)

                }

            }
        }
    )

}