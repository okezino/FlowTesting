package com.example.flowproject

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var testDispatchers: TestDispatchers


    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        mainViewModel = MainViewModel(testDispatchers)

    }


    @Test
    fun `count down flow experimental test`() = runBlocking {

        mainViewModel.countDownFlow.test {
            for (i in 5 downTo 0) {
                testDispatchers.testDispatchers.advanceTimeBy(500L)
                val emission = awaitItem()
                assertThat(emission).isEqualTo(i)
            }

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `square a number`() = runBlocking {
        val job = launch {
            mainViewModel.sharedFlow.test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo(9)
                cancelAndConsumeRemainingEvents()
            }
        }
        mainViewModel.squareNumber(3)
        job.join()
        job.cancel()
    }


}