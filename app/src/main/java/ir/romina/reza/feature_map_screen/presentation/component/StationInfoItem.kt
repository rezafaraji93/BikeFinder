package ir.romina.reza.feature_map_screen.presentation.component

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsBike
import androidx.compose.material.icons.rounded.MyLocation
import androidx.compose.material.icons.rounded.ReduceCapacity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ir.romina.reza.R
import ir.romina.reza.feature_map_screen.domain.model.Station

@Composable
fun StationInfoItem(
    station: Station,
    onStationClicked: () -> Unit,
    onLocateClicked: () -> Unit
) {

    val animatedCapacity = animateIntAsState(targetValue = station.capacity.toInt())

    Box(
        modifier = Modifier
            .height(300.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(16.dp))
            .padding(2.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .background(MaterialTheme.colors.background)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
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
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
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
                    text = "Capacity: ${animatedCapacity.value}",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.body2
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
                    style = MaterialTheme.typography.body2
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
                    style = MaterialTheme.typography.body2
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = onLocateClicked
                ) {
                    Text(
                        text = stringResource(id = R.string.locate),
                        style = MaterialTheme.typography.button
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onStationClicked
                ) {
                    Text(
                        text = stringResource(id = R.string.details),
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }
    }

}