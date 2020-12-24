package com.example.a20202212arielapplebaumnycschoolskotlin.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface SchoolsDao {

    @Query("SELECT * FROM tbl_Schools")
    fun getSchools(): LiveData<List<School>>

    @Query("SELECT * FROM tbl_Schools WHERE name = :name")
    fun getSchoolDetail(name: String) : LiveData<School>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDatabase(school: School)

}