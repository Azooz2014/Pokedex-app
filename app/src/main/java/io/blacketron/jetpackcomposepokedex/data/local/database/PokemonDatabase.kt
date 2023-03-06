package io.blacketron.jetpackcomposepokedex.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.blacketron.jetpackcomposepokedex.data.local.convertor.*
import io.blacketron.jetpackcomposepokedex.data.local.dao.PokeRemoteKeysDAO
import io.blacketron.jetpackcomposepokedex.data.local.dao.PokemonDAO
import io.blacketron.jetpackcomposepokedex.data.local.model.PokeRemoteKeys
import io.blacketron.jetpackcomposepokedex.data.local.model.Pokemon

@Database(entities = [PokeRemoteKeys::class, Pokemon::class], version = 1)
@TypeConverters(
    AbilityListConvertor::class,
    FormListConvertor::class,
    GameIndexListConvertor::class,
    HeldItemListConvertor::class,
    MoveListConvertor::class,
    AnyListConvertor::class,
    SpeciesConvertor::class,
    SpritesConvertor::class,
    StatListConvertor::class,
    TypeListConvertor::class
)
abstract class PokemonDatabase: RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDAO
    abstract fun getPokeRemoteKeysDao(): PokeRemoteKeysDAO
}