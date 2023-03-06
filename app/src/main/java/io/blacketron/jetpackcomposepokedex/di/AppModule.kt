package io.blacketron.jetpackcomposepokedex.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.blacketron.jetpackcomposepokedex.data.local.dao.PokeRemoteKeysDAO
import io.blacketron.jetpackcomposepokedex.data.local.dao.PokemonDAO
import io.blacketron.jetpackcomposepokedex.data.local.database.PokemonDatabase
import io.blacketron.jetpackcomposepokedex.data.remote.PokeApi
import io.blacketron.jetpackcomposepokedex.data.repository.PokeRepositroyImp
import io.blacketron.jetpackcomposepokedex.util.Constants.BASE_URL
import io.blacketron.jetpackcomposepokedex.util.Constants.DATABASE_NAME
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@ExperimentalPagingApi
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(
        api: PokeApi,
        database: PokemonDatabase
    ) = PokeRepositroyImp(api, database)

    @Singleton
    @Provides
    fun provideApi(): PokeApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PokemonDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providePokemonDao(db: PokemonDatabase): PokemonDAO {
        return db.getPokemonDao()
    }

    @Singleton
    @Provides
    fun providePokemonRemoteKeysDao(db: PokemonDatabase): PokeRemoteKeysDAO {
        return db.getPokeRemoteKeysDao()
    }
}