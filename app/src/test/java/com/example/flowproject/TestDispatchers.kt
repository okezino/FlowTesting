package com.example.flowproject

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class TestDispatchers(
) : DispatcherProvider {
    val testDispatchers  = TestCoroutineDispatcher()
    override val main: CoroutineDispatcher = testDispatchers
    override val io: CoroutineDispatcher = testDispatchers
    override val default: CoroutineDispatcher = testDispatchers
}