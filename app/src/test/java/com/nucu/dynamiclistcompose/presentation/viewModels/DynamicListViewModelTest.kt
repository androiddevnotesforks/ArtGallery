package com.nucu.dynamiclistcompose.presentation.viewModels

import app.cash.turbine.test
import com.nucu.dynamiclistcompose.data.actions.DynamicListAction
import com.nucu.dynamiclistcompose.data.api.DynamicListUseCaseApi
import com.nucu.dynamiclistcompose.data.models.ContextType
import com.nucu.dynamiclistcompose.data.models.DynamicListRequestModel
import com.nucu.dynamiclistcompose.presentation.viewModels.DynamicListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DynamicListViewModelTest {

    @Mock
    lateinit var useCase: DynamicListUseCaseApi

    private lateinit var dynamicListViewModel: DynamicListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        dynamicListViewModel = DynamicListViewModel(
            useCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `dynamicListAction should have a initial action`() = runTest {
        dynamicListViewModel.dynamicListAction.test {
            assert(awaitItem() is DynamicListAction.LoadingAction)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `dynamicListAction should have the state provides by useCase`() = runTest {
        val requestModel = DynamicListRequestModel(ContextType.HOME)
        whenever(useCase.get(0, requestModel)).thenReturn(
            flow { emit(DynamicListAction.ErrorAction(Throwable("Error"))) }
        )

        dynamicListViewModel.dynamicListAction.test {
            dynamicListViewModel.load(requestModel)
            awaitItem()
            assert(awaitItem() is DynamicListAction.ErrorAction)
            cancelAndConsumeRemainingEvents()
        }
    }
}