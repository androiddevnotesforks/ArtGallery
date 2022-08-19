package com.nucu.dynamiclistcompose.factories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nucu.dynamiclistcompose.adapters.DynamicListAdapterFactory
import com.nucu.dynamiclistcompose.components.faces.FacesComponentView
import com.nucu.dynamiclistcompose.components.faces.FacesModel
import com.nucu.dynamiclistcompose.data.models.ComponentInfo
import com.nucu.dynamiclistcompose.data.models.ComponentItemModel
import com.nucu.dynamiclistcompose.listeners.DynamicListComponentListener
import com.nucu.dynamiclistcompose.renders.base.RenderType
import javax.inject.Inject

class FacesFactory @Inject constructor(

): DynamicListAdapterFactory {

    override val renders: List<RenderType>
        get() = listOf(
            RenderType.FACES
        )

    @Composable
    override fun CreateComponent(
        component: ComponentItemModel,
        listener: DynamicListComponentListener?,
        componentInfo: ComponentInfo,
    ) {
        FacesComponentView(
            faces = (component.resource as FacesModel).items
        )
    }

    @Composable
    override fun CreateSkeleton() {
        val size = 60.dp
        val roundedText = 6.dp
        val heightText = 13.dp

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(size)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.onPrimary)
                )

                Box(
                    modifier = Modifier
                        .width(size)
                        .height(heightText)
                        .clip(RoundedCornerShape(roundedText))
                        .background(MaterialTheme.colors.onPrimary)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(size)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.onPrimary)
                )

                Box(
                    modifier = Modifier
                        .width(size)
                        .height(heightText)
                        .clip(RoundedCornerShape(roundedText))
                        .background(MaterialTheme.colors.onPrimary)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(size)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.onPrimary)
                )

                Box(
                    modifier = Modifier
                        .width(size)
                        .height(heightText)
                        .clip(RoundedCornerShape(roundedText))
                        .background(MaterialTheme.colors.onPrimary)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(size)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.onPrimary)
                )

                Box(
                    modifier = Modifier
                        .width(size)
                        .height(heightText)
                        .clip(RoundedCornerShape(roundedText))
                        .background(MaterialTheme.colors.onPrimary)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(size)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.onPrimary)
                )

                Box(
                    modifier = Modifier
                        .width(size)
                        .height(heightText)
                        .clip(RoundedCornerShape(roundedText))
                        .background(MaterialTheme.colors.onPrimary)
                )
            }
        }
    }
}