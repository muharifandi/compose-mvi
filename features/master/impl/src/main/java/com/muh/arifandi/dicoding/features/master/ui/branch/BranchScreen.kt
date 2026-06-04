/**
 * Created by Muh. Arifandi on 25/05/2026.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: BranchScreen
 */
package com.muh.arifandi.dicoding.features.master.ui.branch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaAsyncImage
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaCard
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaNavigationBar
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaSearchField
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.master.domain.model.BranchModel
import com.muh.arifandi.dicoding.features.master.ui.branch.state.BranchIntent
import com.muh.arifandi.dicoding.features.master.ui.branch.state.BranchState
import com.muh.arifandi.dicoding.features.master.ui.component.BranchItem

@Composable
fun BranchScreen(
    viewModel: BranchViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BranchScreenContent(
        state = state,
        onIntent = viewModel::processIntent,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BranchScreenContent(
    state: BranchState,
    onIntent: (BranchIntent) -> Unit,
    onBackClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded
        )
    )

    var isMapLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        // Delay map initialization to ensure smooth screen transition
        kotlinx.coroutines.delay(300)
        isMapLoaded = true
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SakaNavigationBar(
                title = "Branch",
                onBackClick = onBackClick,
                backgroundColor = Color.White
            )
        },
        sheetContent = {
            BranchSheetContent(
                query = state.searchQuery,
                branches = state.filteredBranches,
                onSearch = { onIntent(BranchIntent.SearchBranch(it)) },
                onBranchClick = { onIntent(BranchIntent.SelectBranch(it)) },
                onSearchFocus = {
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
            )
        },
        sheetPeekHeight = 400.dp,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetDragHandle = {
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .width(40.dp)
                    .height(4.dp)
                    .background(Color.LightGray, RoundedCornerShape(2.dp))
            )
        },
        sheetContainerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE5E5E5)) // Placeholder for Map color
        ) {
            if (isMapLoaded) {
                // Real Map
                SimulatedMap(
                    branches = state.filteredBranches,
                    selectedBranch = state.selectedBranch
                )
            } else {
                // Loading Placeholder to prevent jank during transition
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.material3.CircularProgressIndicator(
                        color = SakaTheme.colors.primary
                    )
                }
            }
        }
    }
}

@Composable
private fun SimulatedMap(
    branches: List<BranchModel>,
    selectedBranch: BranchModel?
) {
    val firstBranch = branches.firstOrNull()
    val initialPos = if (firstBranch != null) LatLng(firstBranch.latitude, firstBranch.longitude) 
                    else LatLng(-6.2000, 106.8166) // Default Jakarta

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPos, 15f)
    }

    // Auto-center camera when selectedBranch changes
    LaunchedEffect(selectedBranch) {
        selectedBranch?.let { branch ->
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(
                    LatLng(branch.latitude, branch.longitude),
                    17f // Closer zoom for selected branch
                ),
                durationMs = 1000
            )
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = false),
        uiSettings = MapUiSettings(zoomControlsEnabled = false)
    ) {
        branches.forEach { branch ->
            MarkerInfoWindow(
                state = MarkerState(position = LatLng(branch.latitude, branch.longitude)),
                title = branch.name,
                snippet = branch.address,
                onInfoWindowClick = {
                    // Action when info window clicked
                }
            ) {
                CustomInfoWindow(branch)
            }
        }
    }
}

@Composable
private fun CustomInfoWindow(branch: BranchModel) {
    SakaCard(
        modifier = Modifier
            .width(220.dp)
            .padding(8.dp),
        isSmallShadow = true,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SakaAsyncImage(
                model = branch.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = branch.name,
                    style = SakaTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = branch.address,
                    style = SakaTheme.typography.caption2,
                    color = Color.Gray,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
private fun BranchSheetContent(
    query: String,
    branches: List<BranchModel>,
    onSearch: (String) -> Unit,
    onBranchClick: (BranchModel) -> Unit,
    onSearchFocus: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f) // Full screen height when expanded
            .padding(bottom = 16.dp)
    ) {
        SakaSearchField(
            value = query,
            onValueChange = onSearch,
            placeholder = "Bank",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .onFocusEvent { if (it.isFocused) onSearchFocus() },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                }
            ),
            onClear = { onSearch("") }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(branches) { branch ->
                BranchItem(
                    branch = branch,
                    onClick = { onBranchClick(branch) }
                )
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 0.5.dp,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BranchScreenPreview() {
    MyApplicationTheme {
        BranchScreenContent(
            state = BranchState(
                branches = listOf(
                    BranchModel("1", "Bank 1656 Union Street", "Address", "50 m", 0.0, 0.0),
                    BranchModel("2", "Bank Secaucus", "Address", "1,2 km", 0.0, 0.0)
                ),
                filteredBranches = listOf(
                    BranchModel("1", "Bank 1656 Union Street", "Address", "50 m", 0.0, 0.0),
                    BranchModel("2", "Bank Secaucus", "Address", "1,2 km", 0.0, 0.0)
                )
            ),
            onIntent = {},
            onBackClick = {}
        )
    }
}
