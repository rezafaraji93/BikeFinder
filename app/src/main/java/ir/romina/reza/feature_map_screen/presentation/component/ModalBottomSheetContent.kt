package ir.romina.reza.feature_map_screen.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ir.romina.reza.R
import ir.romina.reza.feature_map_screen.domain.model.Station

@Composable
fun ModalBottomSheetContent(
    onLocateClicked: (Station) -> Unit,
    onShowItemDetailClicked: (Station) -> Unit,
    stations: List<Station>
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 150.dp),
            thickness = 2.dp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.stations),
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            contentPadding = PaddingValues(start = 8.dp, bottom = 8.dp)
        ) {
            if (stations.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.noData),
                            style = MaterialTheme.typography.h2
                        )
                    }
                }
            } else {
                items(stations) { station ->
                    StationInfoItem(
                        station = station,
                        onLocateClicked = {
                            onLocateClicked(station)
                        },
                        onStationClicked = {
                            onShowItemDetailClicked(station)
                        }
                    )
                }

            }
        }
    }

}