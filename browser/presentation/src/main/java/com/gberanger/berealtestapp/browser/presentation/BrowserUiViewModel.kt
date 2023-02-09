package com.gberanger.berealtestapp.browser.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemTypeDomainModel
import com.gberanger.berealtestapp.browser.domain.use_cases.BrowserDeleteItemByIdUseCase
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
import javax.inject.Inject

@HiltViewModel
class BrowserUiViewModel @Inject constructor(
    private val sessionGetRootItemDataUseCase: SessionGetRootItemDataUseCase,
    private val browserFetchItemByIdUseCase: BrowserFetchItemByIdUseCase,
    private val browserObserveItemsByIdUseCase: BrowserObserveItemsByIdUseCase,
    private val browserDeleteItemByIdUseCase: BrowserDeleteItemByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<BrowserUiViewState>(BrowserUiViewState.Loading)
    val state: StateFlow<BrowserUiViewState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = BrowserUiViewState.Loading,
    )

    private var job: Job? = null
    private var currentItem: CurrentItem? = null
    private val navigationStack = ArrayDeque<CurrentItem>()

    init {
        job = viewModelScope.launch {
            with(sessionGetRootItemDataUseCase()) {
                currentItem = CurrentItem(id = this.rootItemId, name = this.rootItemName)
                browserFetchItemByIdUseCase(this.rootItemId, true)
                browserObserveItemsByIdUseCase(this.rootItemId)
                    .collect { items ->
                        emitItems(this.rootItemName, items)
                    }
            }
        }
    }

    fun onFolderClicked(id: String, name: String) {
        currentItem?.let {
            navigationStack.add(it)
        }
        browseItem(id = id, folderName = name, refresh = true)
    }
    fun shouldConsumeBackEvent() = navigationStack.isNotEmpty()
    fun onBackPressed() =
        navigationStack.removeLastOrNull()?.let { previousItem ->
            browseItem(id = previousItem.id, folderName = previousItem.name, refresh = false)
        }

    fun onDeleteItemClicked(id: String) {
        viewModelScope.launch {
            browserDeleteItemByIdUseCase(id)
        }
    }
    private fun browseItem(id: String, folderName: String, refresh: Boolean) {
        _state.value = BrowserUiViewState.Loading
        currentItem = CurrentItem(id = id, name = folderName)
        job?.cancel()
        job = viewModelScope.launch {
            if (refresh) {
                browserFetchItemByIdUseCase(id, false)
            }
            browserObserveItemsByIdUseCase(id)
                .collect { items ->
                    emitItems(folderName, items)
                }
        }
    }

    private fun emitItems(folderName: String, items: List<BrowserItemDomainModel>) {
        if (items.isEmpty()) {
            _state.value = BrowserUiViewState.Success.Empty(folderName)
        } else {
            _state.value = BrowserUiViewState.Success.Items(folderName, sortItems(items))
        }
    }

    private fun sortItems(list: List<BrowserItemDomainModel>): List<BrowserItemDomainModel> {
        val sortedItems = mutableListOf<BrowserItemDomainModel>()
        list.groupBy { items ->
            items.type == BrowserItemTypeDomainModel.FOLDER
        }.values.forEach { separatedList ->
            separatedList.sortedBy { it.name.lowercase() }.onEach {
                sortedItems.add(it)
            }
        }
        return sortedItems
    }

    private data class CurrentItem(val id: String, val name: String)
}