package com.example.presentation.base_ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.presentation.base_ui.theme.AppTheme

@Composable
fun AlertDialog(
    modifier: Modifier = Modifier,
    title: String? = null,
    message: String,
    closeDialog: () -> Unit,
) {
    Dialog(
        onDismissRequest = {},
        content = {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(15.dp),
                elevation = 8.dp,
            ) {
                Column(
                    modifier
                        .background(Color.White),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        if (!title.isNullOrEmpty()) {
                            Text(
                                text = title,
                                textAlign = TextAlign.Center,
                                modifier =
                                    Modifier
                                        .padding(top = 5.dp)
                                        .fillMaxWidth(),
                                style = AppTheme.typography.h2,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                        Text(
                            text = message,
                            textAlign = TextAlign.Center,
                            modifier =
                                Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                        )
                    }
                    Divider(modifier = Modifier.fillMaxWidth().height(1.dp), color = Color.Black)
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = closeDialog,
                        ) {
                            Text(
                                text = "Закрыть",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            )
                        }
                    }
                }
            }
        },
    )
}
