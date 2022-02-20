package io.blacketron.jetpackcomposepokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.blacketron.jetpackcomposepokedex.data.remote.PokeApi
import io.blacketron.jetpackcomposepokedex.data.repository.PokeRepositroy
import io.blacketron.jetpackcomposepokedex.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(
        api: PokeApi
    ) = PokeRepositroy(api)

    @Singleton
    @Provides
    fun provideApi(): PokeApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokeApi::class.java)
    }
}