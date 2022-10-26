package com.nucu.dynamiclistcompose.data.renders.base

import com.javi.render.processor.RenderType

interface DynamicListRender<out T> {

    val renders: List<RenderType>
        get() = listOf()

    suspend fun <T> resolve(render: String, resource: T?): Any?
}