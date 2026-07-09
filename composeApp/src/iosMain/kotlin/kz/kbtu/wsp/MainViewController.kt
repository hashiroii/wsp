package kz.kbtu.wsp

import androidx.compose.ui.window.ComposeUIViewController
import kz.kbtu.wsp.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}