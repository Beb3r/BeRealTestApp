package com.gberanger.berealtestapp.persistence.dao

import androidx.room.*
import com.gberanger.berealtestapp.persistence.dao.models.ItemEntityModel
import kotlinx.coroutines.flow.Flow
@Dao
interface ItemDao {
    @Transaction
    fun addItems(items: List<ItemEntityModel>, clear: Boolean) {
        if (clear) {
            deleteItems()
        }
        addItems(items)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItems(items: List<ItemEntityModel>)
    @Query("SELECT * FROM ItemEntityModel WHERE parentId = :id")
    fun observeItemsByParentId(id: String): Flow<List<ItemEntityModel>>

    @Query("DELETE FROM ItemEntityModel WHERE id = :id OR parentId = :id")
    fun deleteItemById(id: String)
    @Query("DELETE FROM ItemEntityModel")
    fun deleteItems()
}