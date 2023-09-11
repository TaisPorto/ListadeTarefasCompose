package com.tais.listadetarefascompose.componentes

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.tais.listadetarefascompose.ui.theme.BLACK
import com.tais.listadetarefascompose.ui.theme.GRAY
import com.tais.listadetarefascompose.ui.theme.LIGHT_PINK
import com.tais.listadetarefascompose.ui.theme.ShapeEditText
import com.tais.listadetarefascompose.ui.theme.WHITE

@Composable
fun CaixaDeTexto2(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType

) {
    OutlinedTextField(
        value = value,
        onValueChange,
        modifier,
        label = {
            Text(text = label)
        },
        maxLines = maxLines,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = BLACK,
            //Quando a caixa estiver ativada
            focusedBorderColor = LIGHT_PINK,
            //Quando label estiver ativado
            focusedLabelColor = LIGHT_PINK,
            backgroundColor = GRAY,
            //Cursor  vai ficar rosa
            cursorColor = LIGHT_PINK
        ),
        shape = ShapeEditText.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}
