package com.gberanger.berealtestapp.persistence.dao.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ItemEntityModel(
    @PrimaryKey val id: String,
    val parentId: String? = null,
    val name: String? = null,
    val modificationDate: String? = null,
    val size: Int? = null,
    val type: Int? = null

)