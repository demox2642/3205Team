package com.example.search.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.presentation.base_ui.theme.AppTheme
import com.example.presentation.search.R
import com.example.search.models.Repository
import com.example.search.models.User
import com.example.search.models.UserRepository
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchScreen(navController: NavController) {
    val vm: SearchScreenVM = hiltViewModel()
    val userRepositoryList = vm.userRepositoryList.collectAsLazyPagingItems()
    val searchText by vm.searchText.collectAsState()
    val loadState by vm.loadingState.collectAsState()

    if (userRepositoryList.loadState.prepend.endOfPaginationReached) {
        vm.changeLoadingState()
    }
    SearchScreenContent(
        userRepositoryList = userRepositoryList,
        download = vm::dounloadingRepository,
        searchText = searchText,
        changeSearchText = vm::changeSearchText,
        search = vm::search,
        loadState = loadState,
    )
}

@Composable
fun SearchScreenContent(
    userRepositoryList: LazyPagingItems<UserRepository>,
    download: (User, Repository) -> Unit,
    searchText: String,
    changeSearchText: (String) -> Unit,
    search: () -> Unit,
    loadState: Boolean,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            TextField(
                value = searchText,
                onValueChange = { text ->
                    changeSearchText(text)
                },
                placeholder = { Text(text = stringResource(R.string.search_edit_title)) },
                colors =
                    TextFieldDefaults.textFieldColors(
                        textColor = AppTheme.colors.systemTextPrimary,
                        disabledPlaceholderColor = AppTheme.colors.systemTextSecondary,
                    ),
                maxLines = 1,
                textStyle = AppTheme.typography.body0,
                leadingIcon = {
                    IconButton(onClick = {
                        keyboardController?.hide()
                        search()
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            tint = AppTheme.colors.colorGraphIndigo,
                            contentDescription = "Search icon",
                        )
                    }
                },
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        IconButton(onClick = { changeSearchText("") }) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                tint = AppTheme.colors.colorGraphIndigo,
                                contentDescription = "Clear icon",
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier =
                    Modifier.fillMaxWidth()
                        .padding(16.dp),
            )
        },
        backgroundColor = AppTheme.colors.systemBackgroundPrimary,
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            if (loadState) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn {
                    items(userRepositoryList.itemCount) { count ->
                        userRepositoryList[count]?.let {
                            UserRepositoryListItem(userRepository = it, download = download)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RepositoryListItem(
    repository: Repository,
    download: (Repository) -> Unit,
) {
    val uriHandler = LocalUriHandler.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier =
                Modifier
                    .weight(3f)
                    .clickable {
                        uriHandler.openUri(repository.htmlUrl)
                    },
            text = repository.name,
            style = AppTheme.typography.body0,
            color = AppTheme.colors.systemTextPrimary,
        )
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                download(repository)
            },
        ) {
            Icon(
                Icons.Default.Download,
                contentDescription = stringResource(id = R.string.loading),
                tint = AppTheme.colors.colorGraphIndigo,
            )
        }
    }
}

@Composable
fun UserRepositoryListItem(
    userRepository: UserRepository,
    download: (User, Repository) -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
        backgroundColor = AppTheme.colors.systemBackgroundSecondary,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
        ) {
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                GlideImage(
                    imageModel = { userRepository.avatar },
                    modifier =
                        Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clip(CircleShape),
                    loading = {
                        Box(modifier = Modifier.matchParentSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    },
                    failure = {
                        Icon(Icons.Default.Person, contentDescription = stringResource(id = R.string.avatar))
                    },
                )

                Text(text = userRepository.name, style = AppTheme.typography.h4, color = AppTheme.colors.systemTextPrimary)
            }
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = stringResource(id = R.string.repository_title),
                    style = AppTheme.typography.h4,
                    color = AppTheme.colors.systemTextPrimary,
                )
                userRepository.repository.forEachIndexed { index, repository ->
                    RepositoryListItem(
                        repository = repository,
                    ) {
                        download(User(id = userRepository.id, name = userRepository.name, avatar = userRepository.avatar), it)
                    }
                    if (index < userRepository.repository.size - 1) {
                        Divider()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserRepositoryListItemPreview() {
    val userRepository =
        UserRepository(
            id = 234234,
            name = "Name",
            avatar = "d",
            repository =
                listOf(
                    Repository(
                        id = 23423,
                        name = "Repository",
                        defaultBranch = "main",
                        htmlUrl = "asdasdf",
                    ),
                ),
        )
    UserRepositoryListItem(userRepository, { _, _ -> })
}
