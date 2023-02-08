package com.gberanger.berealtestapp.browser.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemTypeDomainModel
import com.gberanger.berealtestapp.browser.domain.use_cases.BrowserFetchItemByIdUseCase
import com.gberanger.berealtestapp.browser.domain.use_cases.BrowserObserveItemsByIdUseCase
import com.gberanger.berealtestapp.session.domain.use_cases.SessionGetRootItemDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class BrowserUiViewModel @Inject constructor(
    private val sessionGetRootItemDataUseCase: SessionGetRootItemDataUseCase,
    private val browserFetchItemByIdUseCase: BrowserFetchItemByIdUseCase,
    private val observeItemsByIdUseCase: BrowserObserveItemsByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<BrowserUiViewState>(BrowserUiViewState.Loading)
    val state: StateFlow<BrowserUiViewState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = BrowserUiViewState.Loading,
    )

    private var job: Job? = null
    private var currentId: String? = null
    private val navigationStack = ArrayDeque<String>()
    init {
        job = viewModelScope.launch {
            with(sessionGetRootItemDataUseCase.invoke().rootItemId) {
                currentId = this
                browserFetchItemByIdUseCase.invoke(this)
                observeItemsByIdUseCase.invoke(this)
                    .collect { items ->
                        emitItems(items)
                    }
            }
        }
    }

    fun onItemClicked(id: String, type: BrowserItemTypeDomainModel) {
        if (type == BrowserItemTypeDomainModel.FOLDER) {
            currentId?.let {
                navigationStack.add(it)
            }
            browseItem(id, true)
        }
    }

    fun onBackPressed(): Boolean {
        val previousId = navigationStack.removeLastOrNull()
        return if (previousId != null) {
            browseItem(previousId, false)
            false
        } else {
            true
        }
    }

    private fun browseItem(id: String, refresh: Boolean) {
        _state.value = BrowserUiViewState.Loading
        currentId = id
        job?.cancel()
        job = viewModelScope.launch {
            if (refresh) {
                browserFetchItemByIdUseCase.invoke(id)
            }
            observeItemsByIdUseCase.invoke(id)
                .collect { items ->
                    emitItems(items)
                }
        }
    }

    private fun emitItems(items: List<BrowserItemDomainModel>) {
        if (items.isEmpty()) {
            _state.value = BrowserUiViewState.Empty
        } else {
            _state.value = BrowserUiViewState.Success(items.sortedBy { it.name })
        }
    }
}