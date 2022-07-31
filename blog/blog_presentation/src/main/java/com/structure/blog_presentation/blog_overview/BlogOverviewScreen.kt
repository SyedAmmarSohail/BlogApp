package com.structure.blog_presentation.blog_overview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_presentation.blog_overview.components.SearchTextField
import kotlinx.coroutines.launch
import com.structure.core.R
import com.structure.core_ui.*
import java.util.*


@OptIn(
    ExperimentalMaterialApi::class, ExperimentalPagerApi::class,
    androidx.compose.ui.ExperimentalComposeUiApi::class
)
@Composable
fun BlogOverviewScreen(
    onNavigateToDetail: (BlogModel) -> Unit,
    viewModel: BlogOverviewViewModel = hiltViewModel()
) {
    val tabs = listOf(TabItem.Feature, TabItem.Latest, TabItem.Trending)
    val pagerState = rememberPagerState()
    val state = viewModel.state

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        TopBar(viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        Tabs(tabs = tabs, pagerState = pagerState, viewModel)
        TabsContent(tabs = tabs, pagerState = pagerState, onNavigateToDetail, state)
    }
}

@Composable
fun TopBar(viewModel: BlogOverviewViewModel) {
    var text by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ninja),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.End)
                .clip(CircleShape)
                .size(30.dp),
        )
        Spacer(modifier = Modifier.height(24.dp))
        SearchTextField(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(25.dp))
                .background(color = LightGray),
            text = text,
            onValueChange = {
                text = it
                viewModel.onEvent(BlogOverViewEvent.OnSearchKey(it))
            },
            shouldShowHint = viewModel.state.isHintVisible,
            onSearch = {
//                TODO("tap on search")
            },
            onFocusChanged = {
            viewModel.onEvent(BlogOverViewEvent.OnSearchFocusedChanged(it.isFocused))
            }
        )
    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState, viewModel: BlogOverviewViewModel) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = White,
        contentColor = Color.White,
        indicator = { tabPositions ->
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = {
                    Text(
                        tab.title,
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Gray,
                selected = pagerState.currentPage == index,
                onClick = {
                    viewModel.onEvent(BlogOverViewEvent.OnTabClick(tab.title.uppercase(Locale.getDefault())))
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                modifier = Modifier.clip(RoundedCornerShape(25.dp))
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(
    tabs: List<TabItem>,
    pagerState: PagerState,
    onNavigateToDetail: (BlogModel) -> Unit,
    state: BlogOverviewState
) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen(state) {
            onNavigateToDetail(it)
        }
    }
}
