package com.martins.milton.goktest.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.martins.milton.goktest.base.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class BaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
}