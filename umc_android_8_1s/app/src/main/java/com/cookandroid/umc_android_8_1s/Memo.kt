package com.cookandroid.umc_android_8_1s

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
    @ColumnInfo(name = "content") val content:String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="contentId")val contentId: Int = 0
)

