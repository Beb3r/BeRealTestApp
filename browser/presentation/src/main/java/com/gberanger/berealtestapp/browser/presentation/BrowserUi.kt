package com.gberanger.berealtestapp.browser.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel
import com.gberanger.berealtestapp.browser.domain.models.BrowserItemTypeDomainModel
import com.gberanger.berealtestapp.common.formatters.DateFormatter
import com.gberanger.berealtestapp.design.theme.black
import com.gberanger.berealtestapp.design.theme.dark_grey
import com.gberanger.berealtestapp.design.theme.red
import com.gberanger.berealtestapp.design.theme.white
import java.text.DateFormat
import java.util.*

@Composable
fun BrowserUi(
    viewModel: BrowserUiViewModel = hiltViewModel(),
    onNavigateToSettingsScreen: () -> Unit,
    onNavigateToVisualizer: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val viewState by viewModel.state.collectAsStateWithLifecycle()

    BackHandler(
        enabled = viewModel.shouldConsumeBackEvent(),
        onBack = viewModel::onBackPressed
    )

    BrowserUi(
        viewState = viewState,
        onNavigateToSettingsScreen = onNavigateToSettingsScreen,
        onItemClicked = { id, name, type ->
            if (type == BrowserItemTypeDomainModel.FOLDER) {
                viewModel.onFolderClicked(id, name)
            } else {
                onNavigateToVisualizer(id)
            }
        },
        onDeleteItemClicked = viewModel::onDeleteItemClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowserUi(
    viewState: BrowserUiViewState,
    onNavigateToSettingsScreen: () -> Unit = {},
    onItemClicked: (String, String, BrowserItemTypeDomainModel) -> Unit,
    onDeleteItemClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("Browser") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (viewState is BrowserUiViewState.Success) {
                        title = viewState.folderName
                    }
                    Text(text = title)
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
                is BrowserUiViewState.Success.Empty -> {
                    Empty()
                }
                is BrowserUiViewState.Success.Items -> {
                    Items(
                        paddings = paddings,
                        items = viewState.items,
                        onItemClicked = onItemClicked,
                        onDeleteItemClicked = onDeleteItemClicked,
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
    onItemClicked: (String, String, BrowserItemTypeDomainModel) -> Unit,
    onDeleteItemClicked: (String) -> Unit
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
                    date = DateFormatter.toDate(item.modificationDate, Date(0)),
                    onItemClicked = onItemClicked,
                    onDeleteItemClicked = onDeleteItemClicked
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
    date: Date,
    onItemClicked: (String, String, BrowserItemTypeDomainModel) -> Unit,
    onDeleteItemClicked: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClicked(id, name, type)
        }) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painterResource(type.toDrawable()),
                contentDescription = stringResource(R.string.browser_item_icon_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .padding(16.dp)
                    .align(CenterVertically)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically)
            ) {
                Text(text = name, color = white)
                Text(
                    text = stringResource(
                        R.string.browser_item_modified_date,
                        DateFormat.getDateInstance(DateFormat.MEDIUM).format(date)
                    ),
                    color = white,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            Box(modifier = Modifier.align(CenterVertically)) {
                IconButton(
                    onClick = {
                        expanded = true
                    }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = stringResource(R.string.browser_item_menu_description),
                        tint = white,
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(dark_grey)
                ) {
                    DropdownMenuItem(
                        onClick = {
                            onDeleteItemClicked(id)
                            expanded = false
                        },
                        text = {
                            Text(
                                text = stringResource(R.string.browser_item_menu_delete),
                                color = white
                            )
                        })
                }
            }
        }
    }
}