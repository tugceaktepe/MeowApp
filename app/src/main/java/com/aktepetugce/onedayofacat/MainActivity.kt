package com.aktepetugce.onedayofacat

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aktepetugce.onedayofacat.data.Datasource
import com.aktepetugce.onedayofacat.model.DailyRoutine
import com.aktepetugce.onedayofacat.ui.theme.OneDayOfACatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneDayOfACatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    OneDayOfACatApp()
                }
            }
        }
    }
}

@Composable
fun OneDayOfACatApp() {
    DailyRoutineList(Datasource.routines)
}

@Composable
fun DailyRoutineList(routines: List<DailyRoutine>) {
    Scaffold(
        topBar = { OneDayOfACatTopBar() }
    ) {
        LazyColumn {
            itemsIndexed(routines) { index, item ->
                DailyRoutineItem(routine = item, routineNumber = index + 1)
            }
        }
    }
}

@Composable
fun OneDayOfACatTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.title ),
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun DailyRoutineItem(routine: DailyRoutine, routineNumber: Int, modifier: Modifier = Modifier) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(modifier = Modifier.padding(8.dp)) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Text(
                text = stringResource(id = R.string.daily_routine, routineNumber),
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(routine.title),
                style = MaterialTheme.typography.h3
            )
            Image(
                painter = painterResource(id = routine.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .clickable {
                        expanded = !expanded
                    },
                contentScale = ContentScale.Fit
            )
            if (expanded) {
                Text(
                    text = stringResource(routine.description),
                    style = MaterialTheme.typography.body1
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DailyRoutinePreview() {
    OneDayOfACatTheme() {
        DailyRoutineItem(
            DailyRoutine(R.string.routine1, R.drawable.image1, R.string.routine1_desc),
            1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DailyRoutinePreviewDark() {
    OneDayOfACatTheme(darkTheme = true) {
        DailyRoutineItem(
            DailyRoutine(R.string.routine1, R.drawable.image9, R.string.routine1_desc),
            1
        )
    }
}