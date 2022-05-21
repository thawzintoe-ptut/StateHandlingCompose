package com.ptut.statehandlingcompose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val listState = rememberLazyListState()
    var spinnerExpanded by remember { mutableStateOf(false) }
    var selectedMenuIndex by remember {
        mutableStateOf(0)
    }
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
            .background(color = Color(0xFFf5f5f5))
    ) {
        val menuItems = listOf("Apple", "Ball", "Cat", "Dog", "Elephant", "Florida")
        // pinned when scrolling
        stickyHeader {
            TopAppBar(
                modifier = Modifier.fillMaxWidth()
                    .height(56.dp),
                backgroundColor = Color.Black,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "State Handling",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 22.sp
                        )
                    )
                }
            }
        }
        item {
            Spacer(Modifier.height(20.dp))
            // Spinner
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .border(0.5.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.7f))
                    .background(Color.White)
                    .clickable(
                        onClick = {
                            spinnerExpanded = true
                        }
                    )
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    val (label, iconView) = createRefs()
                    Text(
                        text = menuItems[selectedMenuIndex],
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(label) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(iconView.start)
                                width = Dimension.fillToConstraints
                            },
                    )
                    DropdownMenu(
                        expanded = spinnerExpanded,
                        onDismissRequest = {
                            spinnerExpanded = false
                        },
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                            .background(Color.White)
                    ) {
                        menuItems.forEachIndexed { index, title ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedMenuIndex = index
                                    spinnerExpanded = false
                                }
                            ) {
                                Text(text = title)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewMain() {
    MainScreen()
}
