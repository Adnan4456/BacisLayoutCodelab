package com.example.basiccodelab

 import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
 import androidx.compose.material.icons.Icons
 import androidx.compose.material.icons.filled.AccountCircle
 import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiccodelab.ui.theme.BasicCodeLabTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
             MySootheApp()
        }
    }
}

// Step: MySoothe App - Scaffold
@Composable
fun MySootheApp() {
    // Implement composable here

    Scaffold(
        bottomBar = {SootheBottomNavigation()}
    ) { paddingValues ->
        HomeScreen()
    }
}
// Step: Bottom navigation - Material
@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    // Implement composable here
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
              Icon(
                    Icons.Default.Spa,
                    contentDescription = null
                )
             },
            label = { Text(stringResource(id = R.string.bottom_navigation_home))}
        )

        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = { Text(stringResource(id = R.string.bottom_navigation_profile))}
        )
    }
}

// Step: Home section - Slot APIs
@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content:@Composable () -> Unit
) {
    // Implement composable here
    Column(modifier = modifier) {
        Text(
            stringResource(id = title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .paddingFromBaseline(
                    top = 40.dp,
                    bottom = 8.dp
                )
                .padding(horizontal = 16.dp)
        )
        content ()
    }
}

// Step: Home screen - Scrolling
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    
    Column(
  
        modifier = modifier
           // .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
     SearchBar(Modifier.padding(horizontal = 16.dp))
     HomeSection(title = R.string.align_your_body) {
         AlignYourBodyRow()
     }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}
@Composable
fun SearchBar(
    modifier: Modifier =    Modifier
){
    TextField(value = "",
        onValueChange = {},
        leadingIcon = {
            Icon( Icons.Default.Search, contentDescription = null )
        },
        placeholder = {
          Text(stringResource(id = R.string.placeholder_search))
        },
        colors = TextFieldDefaults.textFieldColors(
            //getting color from material library set by default.
            backgroundColor = MaterialTheme.colors.surface
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

//step : Align your body row
@Composable
fun  AlignYourBodyRow(
    modifier: Modifier = Modifier
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier){

        items(alignYourBodyData){ item ->
            AlignYourBodyElement(
                drawable = item.drawable,
                text =item.text )
        }
    }
}

// Step: Align your body - Alignment
@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable : Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    // Implement composable here

    Column(
        horizontalAlignment =  Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Image(
            painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            stringResource(id = text),
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .paddingFromBaseline(top  = 24.dp , bottom = 8.dp)
        )
    }
}

// Step: Favorite collections grid - LazyGrid
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(120.dp)
    ){
        items(favoriteCollectionsData){item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                text =item.text,
                modifier = Modifier.height(56.dp)
            )
        }
    }
}


// Step: Favorite collection card - Material Surface
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    // Implement composable here

    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        
        Row (
            verticalAlignment = Alignment.CenterVertically ,
            modifier = Modifier.width(192.dp)){
            Image(painterResource(id = drawable) ,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            modifier = Modifier.size(56.dp))
            
            Text(
                stringResource(id = text),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2 , heightDp = 160)
@Composable
fun ScreenContentPreview() {
    BasicCodeLabTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyRowPreview() {
    BasicCodeLabTheme { AlignYourBodyRow() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionCardPreview() {
    BasicCodeLabTheme {
        FavoriteCollectionCard(
            drawable = R.drawable.fc2_nature_meditations,
            text = R.string.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun SearchBarPreview() {
    BasicCodeLabTheme { SearchBar(Modifier.padding(8.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyElementPreview() {
    BasicCodeLabTheme {
        AlignYourBodyElement(
            drawable = R.drawable.ab1_inversions,
            text =  R.string.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionGridPreview() {
    BasicCodeLabTheme {
        FavoriteCollectionsGrid(
            modifier = Modifier.padding(8.dp)

        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeSectionPreview() {
    BasicCodeLabTheme { HomeSection(title = R.string.align_your_body){
        AlignYourBodyRow()
    } }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeScreenPreview() {
    BasicCodeLabTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun BottomNavigationPreview() {
    BasicCodeLabTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MySoothePreview() {
    MySootheApp()
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map {DrawableStringPair(it.first, it.second)}

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }


private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)