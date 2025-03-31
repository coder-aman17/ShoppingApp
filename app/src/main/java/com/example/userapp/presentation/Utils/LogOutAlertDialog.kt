package com.example.userapp.presentation.Utils

import android.R.attr.contentDescription
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.userapp.R

@Composable
fun LogOutAlertDialog (
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismiss) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Icon(imageVector = Icons.Default.Person, contentDescription = "Profile Image",
                    modifier = Modifier.size(80.dp)
                        .clip(CircleShape))

                Spacer(modifier= Modifier.height(16.dp))

                Text(
                    text = "LOG OUT",
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = colorResource(id = R.color.teal_700) )

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Are you sure you want to log out?",
                    textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))

                Row (
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    OutlinedButton(onClick = onDismiss,
                        modifier = Modifier.weight(1f)
                            .height(50.dp),
                        border = BorderStroke(1.dp, colorResource(id = R.color.purple_500))){

                        Text(text = "Cancel",
                            color = colorResource(id = R.color.purple_500),
                            modifier = Modifier.padding(8.dp))


                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = onConfirm,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.purple_200))){
                        Text(text = "Log Out",
                            color = colorResource(id = R.color.white),
                            modifier = Modifier.padding(8.dp))




                    }


                }

            }
        }
    }
    
}