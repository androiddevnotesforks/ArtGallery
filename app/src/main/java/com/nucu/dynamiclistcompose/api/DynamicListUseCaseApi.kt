package com.nucu.dynamiclistcompose.api

import com.nucu.dynamiclistcompose.actions.DynamicListAction
import com.nucu.dynamiclistcompose.data.models.DynamicListRequestModel
import kotlinx.coroutines.flow.Flow

interface DynamicListUseCaseApi {

    suspend fun get(
        page: Int,
        requestModel: DynamicListRequestModel
    ): Flow<DynamicListAction>
}