import  android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tais.listadetarefascompose.R
import com.tais.listadetarefascompose.itemlista.TarefaItem
import com.tais.listadetarefascompose.model.Tarefa
import com.tais.listadetarefascompose.repositorio.TarefasRepositorio
import com.tais.listadetarefascompose.ui.theme.BLACK
import com.tais.listadetarefascompose.ui.theme.PINK
import com.tais.listadetarefascompose.ui.theme.WHITE

import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListadeTarefas(navController: NavController) {

    val tarefasRepositorio = TarefasRepositorio()


    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = PINK,
                title = {
                    Column(
                        modifier = Modifier.padding(top = 25.dp)
                    ) {
                        Text(
                            text = "Lista de Tarefas",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = WHITE
                        )
                    }
                }
            )
        },
        backgroundColor = BLACK,
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                val listaTarefas = tarefasRepositorio.recuperarTarefas().collectAsState (
                    mutableListOf()).value
                val context = LocalContext.current


                LazyColumn {
                    itemsIndexed(listaTarefas) { index, tarefa, ->
                        TarefaItem(tarefa, context = context, navController, listaTarefas)

                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("SalvarTarefa")
                },
                modifier = Modifier
                    .padding(20.dp)
                    .offset(x = 10.dp, y = 10.dp),
                backgroundColor = PINK
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = "Ícone de salvar tarefa"
                )
            }
        }
    )
}

























