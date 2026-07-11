package kz.kbtu.wsp.di

import kz.kbtu.wsp.core.ui.ThemeManager
import kz.kbtu.wsp.feature.files.FilesViewModel
import kz.kbtu.wsp.feature.home.HomeViewModel
import kz.kbtu.wsp.feature.news.NewsDetailViewModel
import kz.kbtu.wsp.feature.news.NewsListViewModel
import kz.kbtu.wsp.feature.profile.ProfileViewModel
import kz.kbtu.wsp.feature.schedule.ScheduleViewModel
import kz.kbtu.wsp.feature.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { ThemeManager() }
    viewModelOf(::HomeViewModel)
    viewModelOf(::ScheduleViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::FilesViewModel)
    viewModelOf(::NewsListViewModel)
    viewModelOf(::NewsDetailViewModel)
}