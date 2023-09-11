@file:Suppress("NonAsciiCharacters", "LocalVariableName")

package com.tais.listadetarefascompose.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tais.listadetarefascompose.componentes.Botao
import com.tais.listadetarefascompose.componentes.CaixaDeTexto2
import com.tais.listadetarefascompose.constantes.Constantes
import com.tais.listadetarefascompose.repositorio.TarefasRepositorio
import com.tais.listadetarefascompose.ui.theme.PINK
import com.tais.listadetarefascompose.ui.theme.RADIO_BUTTON_GREEN_DISABLED
import com.tais.listadetarefascompose.ui.theme.RADIO_BUTTON_GREEN_SELECT
import com.tais.listadetarefascompose.ui.theme.RADIO_BUTTON_RED_DISABLED
import com.tais.listadetarefascompose.ui.theme.RADIO_BUTTON_RED_SELECT
import com.tais.listadetarefascompose.ui.theme.RADIO_BUTTON_YELLOW_DISABLED
import com.tais.listadetarefascompose.ui.theme.RADIO_BUTTON_YELLOW_SELECT
import com.tais.listadetarefascompose.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@Composable
fun SalvarTarefa(
    navController: NavController
)   {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tarefasRepositorio = TarefasRepositorio()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            backgroundColor = PINK,
            title = {
                Column(
                    modifier = Modifier.padding(top = 25.dp)
                ) {
                    Text(
                        text = "Salvar Tarefa",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = WHITE,
                    )
                }

            }
        )
        var tituloTarefa by remember {
            mutableStateOf("")
        }
        var descriçãoTarefa by remember {
            @Suppress("NonAsciiCharacters")
            mutableStateOf("")

        }
        var sem_prioridadeTarefa by remember {
            mutableStateOf(false)

        }
        var prioridadeBaixaTarefa by remember {
            mutableStateOf(false)

        }
        var prioridadeMediaTarefa by remember {
            mutableStateOf(false)

        }
        var prioridadeAltaTarefa by remember {
            mutableStateOf(false)

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            CaixaDeTexto2(
                value = tituloTarefa,
                onValueChange = {
                    tituloTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 10.dp, 0.dp),
                label = "Título Tarefa",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )
            CaixaDeTexto2(
                value = descriçãoTarefa,
                onValueChange = {
                    descriçãoTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                label = "Descrição",
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Nivel de Prioridade")

                RadioButton(
                    selected = prioridadeBaixaTarefa,
                    onClick = {
                        prioridadeBaixaTarefa = !prioridadeBaixaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_GREEN_DISABLED,
                        selectedColor = RADIO_BUTTON_GREEN_SELECT
                    )
                )
                RadioButton(
                    selected = prioridadeMediaTarefa,
                    onClick = {
                        prioridadeMediaTarefa = !prioridadeMediaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_YELLOW_DISABLED,
                        selectedColor = RADIO_BUTTON_YELLOW_SELECT
                    )
                )
                RadioButton(
                    selected = prioridadeAltaTarefa,
                    onClick = {
                        prioridadeAltaTarefa = !prioridadeAltaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_RED_DISABLED,
                        selectedColor = RADIO_BUTTON_RED_SELECT
                    )
                )
            }

                Botao(
                    onClick = {

                        var mensagem = true

                              scope.launch(Dispatchers.IO){
                                  if(tituloTarefa.isEmpty()) {
                                      mensagem = false
                                  }else if (tituloTarefa.isNotEmpty() && descriçãoTarefa.isNotEmpty() && prioridadeBaixaTarefa){
                                      tarefasRepositorio.salvarTarefa(tituloTarefa, descriçãoTarefa,Constantes.PRIORIDADE_BAIXA)
                                      mensagem = true
                                      }else if (tituloTarefa.isNotEmpty() && descriçãoTarefa.isNotEmpty()&& prioridadeMediaTarefa){
                                          tarefasRepositorio.salvarTarefa(tituloTarefa,descriçãoTarefa,Constantes.PRIORIDADE_MEDIA)
                                      mensagem = true
                                  } else if (tituloTarefa.isNotEmpty() && descriçãoTarefa.isNotEmpty()&& prioridadeAltaTarefa){
                                      tarefasRepositorio.salvarTarefa(tituloTarefa,descriçãoTarefa,Constantes.PRIORIDADE_ALTA)
                                      mensagem = true
                                  } else if (tituloTarefa.isNotEmpty() && descriçãoTarefa.isNotEmpty() && sem_prioridadeTarefa){
                                      tarefasRepositorio.salvarTarefa(tituloTarefa,descriçãoTarefa,Constantes.SEM_PRIORIDADE)
                                      mensagem = true

                                  }else if(tituloTarefa.isNotEmpty() && prioridadeBaixaTarefa){
                                      tarefasRepositorio.salvarTarefa(tituloTarefa,descriçãoTarefa, Constantes.PRIORIDADE_BAIXA)
                                      mensagem = true
                                  }else if(tituloTarefa.isNotEmpty() && prioridadeMediaTarefa){
                                      tarefasRepositorio.salvarTarefa(tituloTarefa,descriçãoTarefa, Constantes.PRIORIDADE_MEDIA)
                                      mensagem = true
                                  }else if(tituloTarefa.isNotEmpty() && prioridadeAltaTarefa){
                                      tarefasRepositorio.salvarTarefa(tituloTarefa,descriçãoTarefa, Constantes.PRIORIDADE_ALTA)
                                      mensagem = true
                                  } else {
                                      tarefasRepositorio.salvarTarefa(tituloTarefa,descriçãoTarefa, Constantes.SEM_PRIORIDADE)
                                      mensagem = true
                                  }

                                  }
                        scope.launch(Dispatchers.Main){
                            if (mensagem){
                                Toast.makeText(context, "Sucesso ao salva a tarefa!", Toast.LENGTH_SHORT).show()
                                navController.popBackStack()

                            }else{
                                Toast.makeText(context, "Título tarefa obrigatório", Toast.LENGTH_SHORT).show()

                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(16.dp),
                    texto = "Salvar"
                )
            }
        }
    }
















