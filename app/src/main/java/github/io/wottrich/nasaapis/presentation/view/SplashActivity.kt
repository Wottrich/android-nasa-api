package github.io.wottrich.nasaapis.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import github.io.wottrich.common.compose.theme.ui.theme.NasaTheme
import github.io.wottrich.home.HomeScreen
import github.io.wottrich.nasaapis.presentation.viewmodels.SplashUiEffects
import github.io.wottrich.nasaapis.presentation.viewmodels.SplashViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NasaTheme {
                Scaffold {
                    Screen(
                        onFinishSplash = {
                            startActivity(Intent(this, SingleActivity::class.java))
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun Screen(
    onFinishSplash: () -> Unit,
    viewModel: SplashViewModel = getViewModel(),
) {
    val effects = viewModel.effects
    LaunchedEffect(key1 = effects) {
        effects.collect {
            if (it == SplashUiEffects.FinishSplash) {
                onFinishSplash()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Nasa Apis application")
        Text(text = "By Wottrich")
    }

    SideEffect {
        viewModel.onSideEffect()
    }
}