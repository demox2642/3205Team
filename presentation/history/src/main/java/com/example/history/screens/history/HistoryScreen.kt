package com.example.history.screens.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.history.models.HistoryUserRepo
import com.example.presentation.base_ui.theme.AppTheme
import com.example.presentation.history.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HistoryScreen(navController: NavController) {
    val vm: HistoryScreenVM = hiltViewModel()
    val historyList by vm.historyList.collectAsState()

    HistoryScreenContent(historyList)
}

@Composable
fun HistoryScreenContent(historyList: List<HistoryUserRepo>) {
    Scaffold(
        backgroundColor = AppTheme.colors.systemBackgroundPrimary,
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(historyList) { item ->
                HistoryListItem(item)
            }
        }
    }
}

@Composable
fun HistoryListItem(historyUserRepo: HistoryUserRepo) {
    val uriHandler = LocalUriHandler.current
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                GlideImage(
                    imageModel = { historyUserRepo.avatar },
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

                Text(text = historyUserRepo.name, style = AppTheme.typography.h4, color = AppTheme.colors.systemTextPrimary)
            }
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    modifier =
                        Modifier
                            .clickable {
                                uriHandler.openUri(historyUserRepo.repoLinc)
                            },
                    text = historyUserRepo.repoName,
                    style = AppTheme.typography.body0,
                    color = AppTheme.colors.systemTextPrimary,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HistoryListItemPreview() {
    val historyItem =
        HistoryUserRepo(
            name = "name",
            avatar = "avatar",
            repoName = "reponame",
            repoLinc = "repoLinc",
        )
    HistoryListItem(historyItem)
}