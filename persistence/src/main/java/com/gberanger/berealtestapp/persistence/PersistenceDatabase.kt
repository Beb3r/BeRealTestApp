package com.gberanger.berealtestapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gberanger.berealtestapp.persistence.dao.ItemDao
import com.gberanger.berealtestapp.persistence.dao.models.ItemEntityModel
import com.gromo.persistence.converter.SimpleListConverter

@Database(
    entities = [
        ItemEntityModel::class,
    ],
    version = PersistenceDatabase.DATABASE_VERSION,
)
@TypeConverters(
    SimpleListConverter::class
)
abstract class PersistenceDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_VERSION = 1
    }

    abstract fun itemDao(): ItemDao
}
