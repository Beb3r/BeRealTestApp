package com.gberanger.berealtestapp.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gberanger.berealtestapp.persistence.dao.models.ItemEntityModel
import kotlinx.coroutines.flow.Flow
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItems(photos: List<ItemEntityModel>)

    @Query("SELECT * FROM ItemEntityModel WHERE parentId = :id")
    fun observeItemsByParentId(id: String): Flow<List<ItemEntityModel>>
}