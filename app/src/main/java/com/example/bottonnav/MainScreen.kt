package com.example.bottonnav
import  com.example.bottonnav.pages.SettingsViewModelFactory
import android.app.Application

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottonnav.pages.RoutinesPage
import com.example.bottonnav.pages.SettingsScreen
import com.example.bottonnav.pages.SettingsViewModel
import com.example.bottonnav.pages.favoritesPage
import com.example.bottonnav.pages.thingsPage

@Composable
fun mainScreen(modifier: Modifier = Modifier) {
    val navItemList = listOf(
        Navaitem("Favorites", Icons.Default.Favorite, 0),
        Navaitem("Routines", Icons.Default.ShoppingCart, 0),
        Navaitem("Settings", Icons.Default.Settings, 0),
        Navaitem("Things", Icons.Default.List, 0)

    )
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navaitem ->
                    NavigationBarItem(selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            BadgedBox(badge = {
                                if (navaitem.bagdeCount>0)
                                Badge(){
                                    Text(text = navaitem.bagdeCount.toString())
                                }
                            }) {
                                Icon(imageVector = navaitem.Icon, contentDescription = "Icon")
                            }

                        },
                        label = {
                            Text(text = navaitem.label)
                        })
                }

            }
        }
    ) { innerPadding ->
        contentScreen(modifier = Modifier.padding(innerPadding),selectedIndex)
    }

}

@Composable
fun contentScreen(modifier: Modifier = Modifier, selectedIndex : Int) {
    when(selectedIndex){
        0-> favoritesPage()
        1-> RoutinesPage()
        2 -> SettingsScreenWithViewModel()
        3-> thingsPage()
    }
}
@Composable
fun SettingsScreenWithViewModel() {
    val context = LocalContext.current
    val factory = remember { SettingsViewModelFactory(context.applicationContext as Application) }
    val viewModel: SettingsViewModel = viewModel(factory = factory)

    SettingsScreen(viewModel = viewModel)
}




