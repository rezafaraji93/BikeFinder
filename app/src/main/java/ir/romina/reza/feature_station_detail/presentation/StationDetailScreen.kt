package ir.romina.reza.feature_station_detail.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsBike
import androidx.compose.material.icons.rounded.MyLocation
import androidx.compose.material.icons.rounded.Payment
import androidx.compose.material.icons.rounded.ReduceCapacity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StationDetailScreen(
    viewModel: StationDetailViewModel = hiltViewModel()
) {

    val station = viewModel.station
    val loading = viewModel.isLoading

    Box(modifier = Modifier.fillMaxSize()) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Icon(
                        imageVector = Icons.Rounded.DirectionsBike,
                        contentDescription = station.name,
                        modifier = Modifier.padding(12.dp),
                        tint = MaterialTheme.colors.primary
                    )
                    Text(
                        text = station.name,
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.h4.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.ReduceCapacity,
                        contentDescription = station.name,
                        modifier = Modifier.padding(12.dp),
                        tint = Color.LightGray
                    )
                    Text(
                        text = "Capacity: ${station.capacity}",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.MyLocation,
                        contentDescription = station.name,
                        modifier = Modifier.padding(12.dp),
                        tint = Color.LightGray
                    )
                    Text(
                        text = "Latitude: ${station.lat}",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.MyLocation,
                        contentDescription = station.name,
                        modifier = Modifier.padding(12.dp),
                        tint = Color.LightGray
                    )
                    Text(
                        text = "Longitude: ${station.lng}",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.Payment,
                        contentDescription = station.name,
                        modifier = Modifier.padding(12.dp),
                        tint = Color.LightGray
                    )
                    Text(
                        text = "Longitude: ${station.rental_method}",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
        }
    }



}