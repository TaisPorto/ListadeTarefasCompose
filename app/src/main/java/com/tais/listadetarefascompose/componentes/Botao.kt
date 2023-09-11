package com.tais.listadetarefascompose.componentes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tais.listadetarefascompose.ui.theme.PINK
import com.tais.listadetarefascompose.ui.theme.WHITE
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Botao(
    onClick: () -> Unit,
    modifier: Modifier,
    texto: String
) {
    Button(
        onClick,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
       colors = ButtonDefaults.buttonColors(
         Color(0xFFE91E63),
           contentColor = Color.White

        )

    ) {

        Text(
            text = texto,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}


