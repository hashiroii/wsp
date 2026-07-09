package kz.kbtu.wsp.di

import kz.kbtu.wsp.feature.home.HomeViewModel
import kz.kbtu.wsp.feature.schedule.ScheduleViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::ScheduleViewModel)
}