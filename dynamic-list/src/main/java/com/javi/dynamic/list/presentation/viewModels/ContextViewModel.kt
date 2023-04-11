package com.javi.dynamic.list.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.javi.data.enums.ContextType
import com.javi.dynamic.list.data.actions.ContextViewAction
import com.javi.dynamic.list.data.controllers.DefaultDynamicListController
import com.javi.dynamic.list.data.models.DynamicListRequestModel
import com.javi.dynamic.list.presentation.ui.base.DynamicListStateListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

abstract class ContextViewModel : ViewModel() {

    @Inject
    lateinit var headerAdapterController: DefaultDynamicListController

    @Inject
    lateinit var bodyAdapterController: DefaultDynamicListController

    private val _state = MutableStateFlow<HashMap<String, String>>(hashMapOf())
    internal val state: StateFlow<HashMap<String, String>> = _state

    private val _contextViewAction = MutableStateFlow<ContextViewAction?>(null)
    internal val contextViewAction: StateFlow<ContextViewAction?> = _contextViewAction

    private val _dynamicListStateListener = MutableStateFlow<DynamicListStateListener?>(null)
    val dynamicListStateListener: StateFlow<DynamicListStateListener?> = _dynamicListStateListener

    abstract val context: ContextType

    /**
     * Maybe this can be removed and create abstract properties
     * like state or other params for request model.
     * The idea is only change properties and not all the model
     */
    abstract val requestModel: DynamicListRequestModel

    internal fun sendDynamicListState(dynamicListState: DynamicListStateListener) {
        _dynamicListStateListener.value = dynamicListState
    }
}
