package com.javi.dynamic.list.data.factories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.javi.dynamic.list.data.factories.base.DynamicListFactory
import com.javi.dynamic.list.data.models.ComponentInfo
import com.javi.dynamic.list.data.models.ComponentItemModel
import com.javi.dynamic.list.presentation.components.profile.ProfileComponentScreenView
import com.javi.dynamic.list.presentation.components.profile.ProfileModel
import com.javi.dynamic.list.presentation.components.profile.profileHeight
import com.javi.render.data.RenderType
import com.javi.render.processor.annotations.factory.AdapterFactory
import javax.inject.Inject

@AdapterFactory
class ProfileFactory @Inject constructor(

): DynamicListFactory {

    override val renders: List<RenderType> = listOf(RenderType.PROFILE)

    @Composable
    override fun CreateComponent(
        modifier: Modifier,
        component: ComponentItemModel,
        componentInfo: ComponentInfo
    ) {
        val model = remember {
            derivedStateOf { component.resource as ProfileModel }
        }

        ProfileComponentScreenView(
            model = model.value,
            isExpandedScreen = componentInfo.dynamicListObject.widthSizeClass == WindowWidthSizeClass.Expanded
        )
    }

    @Composable
    override fun CreateSkeleton() {
        Box(
          modifier = Modifier
              .testTag("skeleton")
              .height(profileHeight)
              .fillMaxSize()
              .wrapContentSize(unbounded = true)
              .background(MaterialTheme.colors.primary)
        )
    }
}