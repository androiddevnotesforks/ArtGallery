package com.javi.design.system.atoms

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javi.design.system.extensions.withBounceClick
import com.javi.design.system.theme.DynamicListComposeTheme
import com.javi.design.system.theme.Typography

private const val ANIMATION_DURATION_TIME = 250

@Composable
fun StepperButtonComponentView(
    modifier: Modifier = Modifier,
    quantity: Int = 0,
    onAdd: () -> Unit,
    onDecrement: () -> Unit
) {
    val interactionSource = MutableInteractionSource()
    val focusState = interactionSource.collectIsFocusedAsState()

    val animatedWidth = animateDpAsState(
        targetValue = if (quantity > 0) 140.dp else 0.dp,
        tween(ANIMATION_DURATION_TIME)
    )

    val animatedStepperVisibility = animateFloatAsState(
        targetValue = if (quantity > 0 && focusState.value) 1f else 0f,
        tween(ANIMATION_DURATION_TIME)
    )

    val animatedButtonVisibility = animateFloatAsState(
        targetValue = if (quantity > 0 && focusState.value) 0f else 1f,
        tween(ANIMATION_DURATION_TIME)
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopEnd
    ) {
        StepperButtonAction(
            modifier = Modifier
                .size(45.dp)
                .alpha(animatedButtonVisibility.value),
            icon = Icons.Filled.Add
        ) {
            onAdd()
            interactionSource.tryEmit(FocusInteraction.Focus())
        }

        Row(
            modifier = modifier
                .focusable(true, interactionSource = interactionSource)
                .withBounceClick()
                .height(45.dp)
                .width(animatedWidth.value)
                .alpha(animatedStepperVisibility.value)
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colors.surface),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StepperButtonAction(icon = Icons.Filled.Clear) {
                onDecrement()
            }

            Text(
                text = quantity.toString(),
                style = Typography.h6.copy(fontWeight = FontWeight.Black),
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 15.dp)
            )

            StepperButtonAction(icon = Icons.Filled.Add) {
                onAdd()
            }
        }
    }
}

@Composable
fun StepperButtonAction(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.White)
            .clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp),
            imageVector = icon,
            contentDescription = "action",
            tint = Color.Black
        )
    }
}

@Composable
@Preview
fun PreviewStepperButtonAction() {
    DynamicListComposeTheme {
        StepperButtonAction(icon = Icons.Default.Add) { }
    }
}

@Composable
@Preview
fun PreviewBuyButtonComponentView() {
    DynamicListComposeTheme {
        StepperButtonComponentView(
            quantity = 1,
            onAdd = { },
            onDecrement = { }
        )
    }
}
