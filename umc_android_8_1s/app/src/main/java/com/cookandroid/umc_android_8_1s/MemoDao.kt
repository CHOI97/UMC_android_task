package com.cookandroid.umc_android_8_1s

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoDao {
    @Insert
    fun insert(user: Memo)

    @Delete
    fun delete(user: Memo)

    @Query("SELECT * FROM Memo")
    fun selectAll(): List<Memo>

    @Query("SELECT * FROM Memo WHERE contentId= :contentId")
    fun selectByContentId(contentId: Int): Memo

    @Query("SELECT * FROM Memo WHERE content= :content")
    fun selectByContent(content: String): List<Memo>

    @Query("UPDATE Memo SET content = :content WHERE contentId= :contentId")
    fun updateContentByContentId(contentId: Int, content: String)

    @Query("SELECT * FROM Memo")
    fun MemoLiveSelect(): LiveData<MutableList<Memo>>
}