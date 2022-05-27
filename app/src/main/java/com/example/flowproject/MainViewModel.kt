package com.example.flowproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    /**
     * FLOW OPERATORS
     * We have different types of operators
     * 1... Normal Operators
     * ---filter, map , onEach etc
     *
     * 2... Terminal Operators
     * These types of flow waits and terminates the emission
     * ---count , reduce , fold and flatten to combine flows
     */

    val countDownFlow = flow<Int> {
        val startCount = 10
        var currentValue = startCount
        emit(startCount)
        while (currentValue > 0) {
            delay(500L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        // collectFlow()
        // collectLatest()
        // collectFlowTerminalOperators()
        // collectFlowWithFlat()
    }

    private fun collectLatest() {
        viewModelScope.launch {
            countDownFlow.collectLatest { time ->
                delay(1000)
                println("The current time is $time")

            }
        }
    }
//
//    private fun collectFlow(){
//        viewModelScope.launch {
//            countDownFlow.collect { time ->
//                delay(1000)
//                println("The current time is $time")
//
//            }
//        }
//    }

    /**
     * Normal flow operation
     */
//    private fun collectFlowNormalOperators(){
//        viewModelScope.launch {
//            countDownFlow.filter {
//                it % 2 == 0
//            }.map {
//                it * it
//            }.onEach {
//                println("done $it")
//            }
//                .collect { time ->
//                delay(1000)
//                println("The current time is $time")
//
//            }
//        }
//    }

    /**
     * Terminal flow operation
     */
//    private fun collectFlowTerminalOperators(){
//        viewModelScope.launch {
//           val valueCount =  countDownFlow.
//                    count {
//                        it % 2 == 0
//                    }
//            val valueReduced =  countDownFlow.
//            reduce { accumulator, value ->
//                accumulator *  value + 1
//            }
//            val valueFold =  countDownFlow.
//           fold(100){acc, value ->
//               acc + value
//           }
//            println("The current time is $valueCount")
//            println("The current time is $valueReduced")
//           println("The current time is $valueFold")
//
//        }
//    }
    /**
     * The First flow is the first emitter 1, 2, 3, 4, 5
     * The nested flow is [[2, 3], [3,4],[4,5],[5,6]]
     * The flatten produce 2,3,3,4,4,5,5,6
     */
    private fun collectFlowWithFlat() {
        val flow = (1..5).asFlow()
        viewModelScope.launch {
            flow.flatMapConcat {
                flow {
                    emit(it + 1)
                    delay(500L)
                    emit(it + 2)
                }
            }.collect {
                println(
                    "The value is $it"
                )
            }
        }
    }
}