package com.example.android.superhero.database.model

import androidx.room.Entity

@Entity(primaryKeys = [("url")])
class UrlDatabaseEntity(
    var url: String,
    var timestamp: Long
)