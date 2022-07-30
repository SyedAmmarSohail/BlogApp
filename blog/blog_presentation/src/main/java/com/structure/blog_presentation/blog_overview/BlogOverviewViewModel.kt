package com.structure.blog_presentation.blog_overview

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_domain.use_case.TrackerUseCases
import com.structure.blog_presentation.R
import com.structure.core.domain.model.BlogType
import com.structure.core.domain.preferences.Preferences
import com.structure.core.util.UiEvent
import com.structure.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogOverviewViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    preferences: Preferences
) : ViewModel() {


    var state by mutableStateOf(BlogOverviewState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getBlogs(BlogType.FEATURED)
        preferences.saveShouldShowOnboarding(true)
    }

    fun onEvent(event: BlogOverViewEvent) {
        when (event) {
            BlogOverViewEvent.onMarkFavorite -> TODO()
            is BlogOverViewEvent.onTabClick -> {
                getBlogByType(BlogType.valueOf(event.title))
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun getBlogByType(blogType: BlogType) {
        state = state.copy(blogs = emptyList())
//        viewModelScope.launch {
        trackerUseCases.searchBlog(blogType).onEach { blogs ->
            state = state.copy(blogs = blogs)
        }.launchIn(viewModelScope)
//            state =
//                state.copy(
//                    blogs = ent)
//        }
    }

    private fun getBlogs(blogType: BlogType) {
        state = state.copy(
            isSearching = true,
            blogs = dummyBlogList
        )
        viewModelScope.launch {
            delay(4000)
            trackerUseCases.getBlog().onSuccess { blogList ->
                trackerUseCases.storeBlogs(blogList)
                state =
                    state.copy(blogs = blogList.filter { it.type == blogType }, isSearching = false)
            }.onFailure {
                state = state.copy(isSearching = false)
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_something_went_wrong)
                    )
                )
            }
        }
    }

}