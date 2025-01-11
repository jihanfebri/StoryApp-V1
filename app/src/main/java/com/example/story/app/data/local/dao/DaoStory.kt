package com.example.story.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.story.app.data.local.entity.StoryEntity

@Dao
interface DaoStory {

    @Query("SELECT * FROM tbl_story")
    fun getAllStories(): List<StoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStories(storyList: List<StoryEntity>)

    @Query("DELETE FROM tbl_story")
    suspend fun deleteAllStories()

}