package com.kamalnayan.moviesearcher.di

import com.kamalnayan.data.repository.MovieRepository
import com.kamalnayan.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** @Author Kamal Nayan
Created on: 26/01/24
 **/

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindMovieRepository(movieRepository: MovieRepository): IMovieRepository

}