@file:OptIn(ExperimentalFoundationApi::class)

package com.nucu.dynamiclistcompose.presentation.ui.base

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import com.nucu.dynamiclistcompose.data.actions.ScrollAction
import com.nucu.dynamiclistcompose.data.models.ComponentInfo
import com.nucu.dynamiclistcompose.data.models.DynamicListElement
import com.nucu.dynamiclistcompose.presentation.ui.components.showCase.ShowCaseState

@Composable
fun DynamicListScreen(
    content: List<DynamicListElement>,
    listState: LazyListState,
    widthSizeClass: WindowWidthSizeClass,
    showCaseState: ShowCaseState,
    withVerticalPadding: Boolean = true,
    onAction: ((ScrollAction) -> Unit)? = null
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.wrapContentHeight().testTag("dynamic-list-container"),
        state = listState,
        contentPadding = PaddingValues(
            bottom = if (content.isNotEmpty() && withVerticalPadding) 16.dp else 0.dp,
            top = if (withVerticalPadding) 16.dp else 0.dp
        )
    ) {

        items(items = content, key = { it.componentItemModel.index }) {
            it.factory?.CreateComponent(
                modifier = Modifier.animateItemPlacement(),
                component = it.componentItemModel,
                componentInfo = ComponentInfo(
                    scrollAction = onAction,
                    windowWidthSizeClass = widthSizeClass,
                    showCaseState = showCaseState
                )
            )
        }
    }
}