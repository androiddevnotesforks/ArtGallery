package com.nucu.dynamiclistcompose.components.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nucu.dynamiclistcompose.data.models.tooltip.ShowCaseStrategy
import com.nucu.dynamiclistcompose.renders.base.RenderType
import com.nucu.dynamiclistcompose.ui.components.showCase.ShowCaseState
import com.nucu.dynamiclistcompose.ui.components.showCase.ShowCaseStyle
import com.nucu.dynamiclistcompose.ui.components.showCase.TooltipView
import com.nucu.dynamiclistcompose.ui.components.showCase.asShowCaseTarget
import com.nucu.dynamiclistcompose.ui.components.showCase.models.ShapeType
import com.nucu.dynamiclistcompose.ui.components.showCase.rememberShowCaseState
import com.nucu.dynamiclistcompose.viewModels.CardsViewModel
import com.nucu.dynamiclistcompose.ui.theme.Typography

private const val MAX_ELEMENTS = 3

@OptIn(ExperimentalUnitApi::class)
@Composable
fun CardsComponentView(
    data: CardsModel,
    componentIndex: Int,
    showCaseState: ShowCaseState,
    viewModel: CardsViewModel = hiltViewModel(),
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {

        val context = LocalContext.current

        val decoration by remember {
            derivedStateOf {
                TextDecoration.Underline
            }
        }

        val letterSpacing by remember {
            derivedStateOf {
                TextUnit(40f, TextUnitType.Sp)
            }
        }

        val titleUpperCase by remember {
            derivedStateOf { data.title.uppercase() }
        }

        Text(
            text = titleUpperCase,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(),
            style = Typography.h6,
            color = MaterialTheme.colors.secondary,
            letterSpacing = letterSpacing,
            textAlign = TextAlign.Center,
            textDecoration = decoration
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        ) {
            itemsIndexed(items = data.cardElements) { index, item ->

                val modifier = if (index == 0) {
                    Modifier.asShowCaseTarget(
                        index = componentIndex,
                        style = ShowCaseStyle.Default.copy(
                            shapeType = ShapeType.RECTANGLE,
                            cornerRadius = 16.dp,
                            withAnimation = false
                        ),
                        content = {
                            TooltipView(text = "Esto es un card dentro de un carrusel")
                        },
                        strategy = ShowCaseStrategy(firstToHappen = true),
                        key = RenderType.CARDS.value,
                        state = showCaseState
                    )
                } else Modifier

                val pictures by remember {
                    derivedStateOf {
                        item.images.take(MAX_ELEMENTS)
                    }
                }

                Card(
                    modifier = modifier
                        .wrapContentWidth()
                        .height(100.dp)
                        .clickable {
                            viewModel.navigateToCardsDetail(item.title,
                                item.images.map { it.imageURL })
                        },
                    shape = RoundedCornerShape(12.dp),
                    elevation = 5.dp
                ) {

                    Column(
                        modifier = Modifier
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = item.title,
                            color = MaterialTheme.colors.secondary,
                            style = Typography.button
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {

                            pictures.forEach { cardImage ->
                                AsyncImage(
                                    modifier = Modifier
                                        .size(46.dp)
                                        .clip(RoundedCornerShape(5.dp)),
                                    model = ImageRequest.Builder(context)
                                        .data(cardImage.imageURL)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop
                                )
                            }

                            if (item.images.size > MAX_ELEMENTS) {
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .clip(RoundedCornerShape(16.dp))
                                ) {
                                    Text(
                                        text = "+${item.images.size - MAX_ELEMENTS}",
                                        style = Typography.button
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCardsComponentView() {
    CardsComponentView(
        data = CardsModel(
            cardElements = listOf(
                CardElement("Hola", images = emptyList())
            ),
            title = "Title",
        ),
        componentIndex = 0,
        showCaseState = rememberShowCaseState()
    )
}