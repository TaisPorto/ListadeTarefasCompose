@file:Suppress("NonAsciiCharacters")

package com.tais.listadetarefascompose.itemlista

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.tais.listadetarefascompose.R
import com.tais.listadetarefascompose.model.Tarefa
import com.tais.listadetarefascompose.repositorio.TarefasRepositorio
import com.tais.listadetarefascompose.ui.theme.RADIO_BUTTON_GREEN_SELECT
import com.tais.listadetarefascompose.ui.theme.RADIO_BUTTON_RED_SELECT
import com.tais.listadetarefascompose.ui.theme.RADIO_BUTTON_YELLOW_SELECT
import com.tais.listadetarefascompose.ui.theme.ShapeCarPrioridade
import com.tais.listadetarefascompose.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@Composable
fun TarefaItem(
    tarefa: Tarefa,
    context: Context,
    navController: NavController,
    listaTarefas: MutableList<Tarefa>

) {

    val tituloTarefa = tarefa.tarefa
    val descriçaoTarefa = tarefa.descricao
    val prioridade = tarefa.prioridade
    val index = listaTarefas.indexOf(tarefa)


    val scope = rememberCoroutineScope()
    val tarefasRepositorio = TarefasRepositorio()

    fun dialogDeletar(){

        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deletar Tarefa")
            .setMessage("Deseja excluir a tarefa?")
            .setPositiveButton("Sim"){_,_ ->

                tarefasRepositorio.deletarTarefa(tituloTarefa.toString())

                listaTarefas.removeAt(index)

                    scope.launch(Dispatchers.Main){
                        navController.navigate("ListadeTarefas")
                        Toast.makeText(context,"Sucesso ao deletar !", Toast.LENGTH_SHORT).show()

                    }
                }

            .setNegativeButton("Não"){_,_->

            }
            .show()
    }

    val nivelDePrioridade: String = when (prioridade) {
        0 -> {
            "Sem prioridade"
        }

        1 -> {
            "Prioridade Baixa"
        }

        2 -> {
            "Prioridade Media"
        }

        else -> {
            "Prioridade Alta"
        }
    }
    val color = when (prioridade) {
        0 -> {
            Color.Black
        }

        1 -> {
            RADIO_BUTTON_GREEN_SELECT
        }

        2 -> {
            RADIO_BUTTON_YELLOW_SELECT
        }

        else -> {
            RADIO_BUTTON_RED_SELECT
        }
    }


    androidx.compose.material.Card(
        backgroundColor = WHITE,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(10.dp)
        ) {
            val (txtTitulo, txtDescricao, cardPrioridade, txtPrioridade, btDeletar) = createRefs()
            Text(
                text = tituloTarefa.toString(),
                modifier = Modifier.constrainAs(txtDescricao) {
                    top.linkTo(txtTitulo.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Text(
                text = descriçaoTarefa.toString(),
                modifier = Modifier.constrainAs(txtTitulo) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Text(
                text = nivelDePrioridade,
                modifier = Modifier.constrainAs(txtPrioridade) {
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )
            androidx.compose.material.Card(
                backgroundColor = color,
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(cardPrioridade) {
                        top.linkTo(txtDescricao.bottom, margin = 10.dp)
                        start.linkTo(txtPrioridade.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)

                    },
                shape = ShapeCarPrioridade.large
            ) {

            }
            IconButton(
                onClick = {
                          dialogDeletar()

             },
                modifier = Modifier.constrainAs(btDeletar){
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(cardPrioridade.end, margin = 30.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            ) {
                Image( imageVector = ImageVector.vectorResource(id= R.drawable.ic_delete) , contentDescription = null)

            }

        }
    }
}
