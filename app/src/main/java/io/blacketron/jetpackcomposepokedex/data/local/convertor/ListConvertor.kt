package io.blacketron.jetpackcomposepokedex.data.local.convertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import io.blacketron.jetpackcomposepokedex.data.remote.response.*

abstract class ListConvertor<T> {

    @TypeConverter
    fun fromList(value: List<T>): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun toList(value: String): List<T> {
        return try {
            Gson().fromJson<List<T>>(value)
        } catch (e: Exception) {
            listOf<T>()
        }
    }
}

class AbilityListConvertor: ListConvertor<Ability>()
class FormListConvertor: ListConvertor<Form>()
class GameIndexListConvertor: ListConvertor<GameIndex>()
class HeldItemListConvertor: ListConvertor<HeldItem>()
class MoveListConvertor: ListConvertor<Move>()
class AnyListConvertor: ListConvertor<Any>()

class StatListConvertor: ListConvertor<Stat>()
class TypeListConvertor: ListConvertor<Type>()