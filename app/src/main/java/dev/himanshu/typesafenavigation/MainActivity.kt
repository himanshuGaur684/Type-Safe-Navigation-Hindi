package dev.himanshu.typesafenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.himanshu.typesafenavigation.ui.theme.TypeSafeNavigationTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TypeSafeNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = AuthDest.Root) {

                        // auth
                        navigation<AuthDest.Root>(startDestination = AuthDest.AuthScreen) {
                            composable<AuthDest.AuthScreen> {
                                AuthScreen {
                                    navController.navigate(ProfileDest.ProfileScreen)
                                }


                            }
                        }

                        //profile
                        navigation<ProfileDest.Root>(startDestination = ProfileDest.ProfileScreen) {
                            composable<ProfileDest.ProfileScreen> {
                                ProfileScreen {
                                    navController.popBackStack()
                                }
                            }
                        }

                    }

                }
            }
        }
    }
}

sealed interface AuthDest {

    @Serializable
    data object Root : AuthDest

    @Serializable
    data object AuthScreen : AuthDest


}

@Composable
fun AuthScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onClick) {
            Text("Auth Screen")
        }
    }

}


sealed interface ProfileDest {

    @Serializable
    data object Root : ProfileDest

    @Serializable
    data object ProfileScreen : ProfileDest

}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onClick) {
            Text("PRofile Screen")
        }
    }

}