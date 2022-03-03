package com.nucu.dynamiclistcompose.impl

import com.nucu.dynamiclistcompose.controllers.DynamicListController
import com.nucu.dynamiclistcompose.models.ComponentItemModel
import com.nucu.dynamiclistcompose.models.DynamicListAction
import com.nucu.dynamiclistcompose.models.DynamicListContainer
import com.nucu.dynamiclistcompose.models.DynamicListRequestModel
import com.nucu.dynamiclistcompose.renders.base.RenderType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DynamicListControllerImpl @Inject constructor() : DynamicListController {

    override suspend fun get(
        page: Int,
        requestModel: DynamicListRequestModel
    ): Flow<DynamicListAction> = flow {
        emit(DynamicListAction.LoadingAction)
        // Emulate response delay
        delay(2500)

        try {

            // Hardcoded data :D
            val bodies = listOf(

                ComponentItemModel(
                    render = RenderType.TOBACCO_PREFERENCE.value,
                    resource = "",
                    name = "",
                    resolver = "",
                    index = 0,
                    uniqueId = ""
                ),

                ComponentItemModel(
                    render = RenderType.BANNER_IMAGE.value,
                    resource = "",
                    name = "",
                    resolver = "",
                    index = 0,
                    uniqueId = ""
                ),

                ComponentItemModel(
                    render = RenderType.ONE_CLICK_REORDER.value,
                    resource = "",
                    name = "",
                    resolver = "",
                    index = 0,
                    uniqueId = ""
                ),

                ComponentItemModel(
                    render = RenderType.TOBACCO_PREFERENCE.value,
                    resource = "",
                    name = "",
                    resolver = "",
                    index = 0,
                    uniqueId = ""
                ),

                ComponentItemModel(
                    render = RenderType.BANNER_IMAGE.value,
                    resource = "",
                    name = "",
                    resolver = "",
                    index = 0,
                    uniqueId = ""
                ),

                ComponentItemModel(
                    render = RenderType.ONE_CLICK_REORDER.value,
                    resource = "",
                    name = "",
                    resolver = "",
                    index = 0,
                    uniqueId = ""
                ),

                ComponentItemModel(
                    render = RenderType.TOBACCO_PREFERENCE.value,
                    resource = "",
                    name = "",
                    resolver = "",
                    index = 0,
                    uniqueId = ""
                ),

                ComponentItemModel(
                    render = RenderType.BANNER_IMAGE.value,
                    resource = "",
                    name = "",
                    resolver = "",
                    index = 0,
                    uniqueId = ""
                ),

                ComponentItemModel(
                    render = RenderType.ONE_CLICK_REORDER.value,
                    resource = "",
                    name = "",
                    resolver = "",
                    index = 0,
                    uniqueId = ""
                )
            )

            // Response...
            val container = DynamicListContainer(
                headers = emptyList(),
                bodies = bodies,
                footers = emptyList()
            )

            emit(DynamicListAction.SuccessAction(container))

        } catch (e: Exception) {
            emit(DynamicListAction.ErrorAction(e))
        }

    }
}