package com.example.metmuseum.di

import com.example.metmuseum.network.MuseumRepository
import com.example.metmuseum.network.MuseumRepositoryImpl
import com.example.metmuseum.network.RemoteMuseumDataSource
import com.example.metmuseum.network.RemoteMuseumDataSourceImpl
import com.example.metmuseum.ui.details.DetailsViewModel
import com.example.metmuseum.ui.search.SearchViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::DetailsViewModel)

    singleOf(::MuseumRepositoryImpl) bind MuseumRepository::class
    singleOf(::RemoteMuseumDataSourceImpl) bind RemoteMuseumDataSource::class
}
