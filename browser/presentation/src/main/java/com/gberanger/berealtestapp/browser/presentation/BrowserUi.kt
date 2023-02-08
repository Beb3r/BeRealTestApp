package com.gberanger.berealtestapp.browser.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemTypeDomainModel
import com.gberanger.berealtestapp.common.composables.BackPressHandler
import com.gberanger.berealtestapp.design.theme.black
import com.gberanger.berealtestapp.design.theme.red
import com.gberanger.berealtestapp.design.theme.white

@Composable
fun BrowserUi(
    viewModel: BrowserUiViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
    onNavigateToSettingsScreen: () -> Unit,
    modifier: Modifier = Modifier
) {

    val viewState by viewModel.state.collectAsStateWithLifecycle()

    BackPressHandler(
        onBackPressed = viewModel::onBackPressed,
        dispatch = onNavigateUp
    )

    BrowserUi(
        viewState = viewState,
        onNavigateToSettingsScreen = onNavigateToSettingsScreen,
        onItemClicked = viewModel::onItemClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowserUi(
    viewState: BrowserUiViewState,
    onNavigateToSettingsScreen: () -> Unit = {},
    onItemClicked: (String, BrowserItemTypeDomainModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Browser")
                },
                actions = {
                    IconButton(onClick = onNavigateToSettingsScreen) {
                        Icon(
                            imageVector = Icons.Rounded.Settings,
                            contentDescription = stringResource(R.string.browser_top_bar_action_description),
                            tint = white,
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black,
                    scrolledContainerColor = black,
                    navigationIconContentColor = white,
                    titleContentColor = white,
                    actionIconContentColor = white,
                )
            )
        },
        modifier = modifier.fillMaxSize()
    ) { paddings ->

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (viewState) {
                is BrowserUiViewState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Center)
                    )
                }
                is BrowserUiViewState.Error -> {
                    Text(
                        text = stringResource(R.string.browser_error_unknown),
                        color = red,
                        modifier = Modifier.align(Center)
                    )
                }
                is BrowserUiViewState.Empty -> {
                    Empty()
                }
                is BrowserUiViewState.Success -> {
                    Items(
                        paddings = paddings,
                        items = viewState.items,
                        onItemClicked = onItemClicked
                    )
                }
            }
        }
    }
}

private fun BrowserItemTypeDomainModel.toDrawable() =
    when (this) {
        BrowserItemTypeDomainModel.FOLDER -> R.drawable.ic_folder
        BrowserItemTypeDomainModel.FILE_IMAGE -> R.drawable.ic_image
        BrowserItemTypeDomainModel.FILE_TEXT -> R.drawable.ic_file
        else -> R.drawable.ic_unknown
    }

@Composable
private fun Empty() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.ic_empty),
            contentDescription = stringResource(R.string.browser_empty_icon_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(64.dp)
        )
        Text(text = stringResource(R.string.browser_empty), color = white)
    }
}

@Composable
private fun Items(
    paddings: PaddingValues,
    items: List<BrowserItemDomainModel>,
    onItemClicked: (String, BrowserItemTypeDomainModel) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = paddings
    ) {
        items.forEach { item ->
            item {
                Item(
                    id = item.id,
                    type = item.type,
                    name = item.name,
                    onItemClicked = onItemClicked
                )
            }
        }
    }
}

@Composable
private fun Item(
    id: String,
    type: BrowserItemTypeDomainModel,
    name: String,
    onItemClicked: (String, BrowserItemTypeDomainModel) -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClicked(id, type)
        }) {
        Row() {
            Image(
                painterResource(type.toDrawable()),
                contentDescription = stringResource(R.string.browser_item_icon_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            Text(text = name, color = white, modifier = Modifier.align(CenterVertically))
        }
    }
}