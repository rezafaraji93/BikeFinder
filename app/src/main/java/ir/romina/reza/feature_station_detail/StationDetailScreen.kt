package ir.romina.reza.feature_station_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ir.romina.reza.feature_map_screen.domain.model.Station

@Composable
fun StationDetailScreen(
    station: Station
) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Text(
            text = station.name,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
        Divider(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp))
        Text(
            text = "Capacity: ${station.capacity}",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Latitude: ${station.lat}",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Longitude: ${station.lng}",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.body1
        )

    }

}