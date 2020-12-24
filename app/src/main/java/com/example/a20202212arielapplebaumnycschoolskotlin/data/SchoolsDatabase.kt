package com.example.a20202212arielapplebaumnycschoolskotlin.data

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a20202212arielapplebaumnycschoolskotlin.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

abstract class SchoolsDatabase : RoomDatabase(){

    abstract fun schoolsDao() : SchoolsDao

    class Callback @Inject constructor(
        private val database: Provider<SchoolsDatabase>,
        @ApplicationScope private val applicationScope : CoroutineScope
    ) : RoomDatabase.Callback()

}