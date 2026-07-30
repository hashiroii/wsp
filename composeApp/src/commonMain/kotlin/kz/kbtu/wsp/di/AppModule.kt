package kz.kbtu.wsp.di

import kz.kbtu.wsp.core.data.repository.FakeFilesRepository
import kz.kbtu.wsp.core.data.repository.FakeNewsRepository
import kz.kbtu.wsp.core.data.repository.FakeProfileRepository
import kz.kbtu.wsp.core.data.repository.FakeScheduleRepository
import kz.kbtu.wsp.core.domain.repository.FilesRepository
import kz.kbtu.wsp.core.domain.repository.NewsRepository
import kz.kbtu.wsp.core.domain.repository.ProfileRepository
import kz.kbtu.wsp.core.domain.repository.ScheduleRepository
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
    single<ProfileRepository> { FakeProfileRepository() }
    single<NewsRepository> { FakeNewsRepository() }
    single<ScheduleRepository> { FakeScheduleRepository() }
    single<FilesRepository> { FakeFilesRepository() }
    viewModelOf(::HomeViewModel)
    viewModelOf(::ScheduleViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::FilesViewModel)
    viewModelOf(::NewsListViewModel)
    viewModelOf(::NewsDetailViewModel)
}