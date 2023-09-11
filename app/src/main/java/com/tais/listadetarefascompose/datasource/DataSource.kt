package com.tais.listadetarefascompose.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.tais.listadetarefascompose.model.Tarefa
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class DataSource {

    private val db = FirebaseFirestore.getInstance()
    private val _todastarefas = MutableStateFlow< MutableList<Tarefa>>(mutableListOf())
    private val todastarefas : StateFlow<MutableList<Tarefa>> = _todastarefas

    fun salvarTarefa(tarefa: String, descricao: String, prioridade: Int) {
        val tarefaMap = hashMapOf(
            "tarefa" to tarefa,
            "descricao" to descricao,
            "prioridade" to prioridade
        )

        db.collection("tarefas").document(tarefa).set(tarefaMap).addOnCompleteListener {

        }.addOnFailureListener {

        }

    }

    fun recuperarTarefas() : Flow<MutableList <Tarefa>>{

        val listadeTarefas: MutableList<Tarefa> = mutableListOf()

        db.collection("tarefas").get().addOnCompleteListener { querySnapshot ->
            if (querySnapshot.isSuccessful) {
                for (documento in querySnapshot.result) {
                    val tarefa = documento.toObject(Tarefa:: class.java)
                    listadeTarefas.add(tarefa)
                    _todastarefas.value = listadeTarefas
                }
            }
        }
        return todastarefas
    }
    fun deletarTarefa( tarefa: String){
        db.collection("tarefas").document(tarefa).delete().addOnCompleteListener {

        }.addOnFailureListener {


        }
    }
}
