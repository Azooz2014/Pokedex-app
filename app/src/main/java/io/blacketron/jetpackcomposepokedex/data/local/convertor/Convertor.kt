package io.blacketron.jetpackcomposepokedex.data.local.convertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.blacketron.jetpackcomposepokedex.data.remote.response.Species
import io.blacketron.jetpackcomposepokedex.data.remote.response.Sprites

abstract class Convertor<T> {

    val type = object : TypeToken<T>() {}.type

    @TypeConverter
    fun from(value: T): String {

        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun to(value: String): T {
        val gson = Gson()

        return gson.fromJson(value, type)

        /*return try {

        } catch (e: Exception) {
            throw IllegalArgumentException()
        }*/
    }
}

/*class SpeciesConvertor: Convertor<Species>()
class SpritesConvertor: Convertor<Sprites>()*/

class SpeciesConvertor{
    val gson = Gson()

    @TypeConverter
    fun toString(obj: Species): String{
        return gson.toJson(obj)
    }

    @TypeConverter
    fun toObj(obj: String): Species{
        return gson.fromJson(obj)
    }
}
class SpritesConvertor {

    val gson = Gson()
    @TypeConverter
    fun toString(obj: Sprites): String{
        return gson.toJson(obj)
    }

    @TypeConverter
    fun toObj(obj: String): Sprites{
        return gson.fromJson(obj)
    }
}