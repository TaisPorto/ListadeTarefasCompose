package com.tais.listadetarefascompose

import ListadeTarefas
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tais.listadetarefascompose.ui.theme.ListaDeTarefasComposeTheme
import com.tais.listadetarefascompose.view.SalvarTarefa

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ListaDeTarefasComposeTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()


                NavHost(navController = navController, startDestination = "ListadeTarefas") {
                    composable(
                        route = "ListadeTarefas"
                    ) {
                        ListadeTarefas(navController)
                    }
                    composable(
                        route = "SalvarTarefa"

                    ) {
                        SalvarTarefa(navController)
                    }
                }
            }
        }
    }

}



