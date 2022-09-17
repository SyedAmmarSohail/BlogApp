package com.structure.blog_presentation.blog_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.structure.blog_domain.use_case.BlogUseCases
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
    private val blogUseCases: BlogUseCases,
    preferences: Preferences
) : ViewModel() {

    var state by mutableStateOf(BlogOverviewState(isSearching = true, blogs = dummyBlogList))
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getBlogs(BlogType.FEATURED)
        preferences.saveShouldShowOnboarding(true)
    }

    fun onEvent(event: BlogOverViewEvent) {
        when (event) {
            BlogOverViewEvent.OnMarkFavorite -> TODO()
            is BlogOverViewEvent.OnTabClick -> {
                getBlogByType(BlogType.valueOf(event.title))
            }
            is BlogOverViewEvent.OnSearchKey -> {
                state= state.copy(searchKey = event.key)
            }
            is BlogOverViewEvent.OnSearchFocusedChanged -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.searchKey.isBlank()
                )
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun getBlogByType(blogType: BlogType) {
        state = state.copy(blogs = emptyList())
        blogUseCases.searchBlog(blogType).onEach { blogs ->
            state = state.copy(blogs = blogs)
        }.launchIn(viewModelScope)
    }

    private fun getBlogs(blogType: BlogType) {
        viewModelScope.launch {
            delay(1000)
            blogUseCases.getBlog().onSuccess { blogList ->
                blogUseCases.storeBlogs(blogList)
                state =
                    state.copy(blogs = blogList.filter { it.type == blogType }, isSearching = false)
            }.onFailure {
                state = state.copy(blogs = emptyList(), isSearching = false)
                _uiEvent.send(
                    UiEvent.ShowToast(
                        UiText.StringResource(R.string.error)
                    )
                )
            }
        }
    }

}