package com.example.a20202212arielapplebaumnycschoolskotlin.di

import android.app.Application
import androidx.room.Room
import com.example.a20202212arielapplebaumnycschoolskotlin.data.SchoolsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(Application::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDatabase(app : Application,
                        callback : SchoolsDatabase.Callback) =
        Room.databaseBuilder(app,SchoolsDatabase::class.java,"schools_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Provides
    fun provideSchoolsDao(db : SchoolsDatabase) = db.schoolsDao()


    @ApplicationScope
    @Provides
    @Singleton
    fun ProvideApplicationScope() = CoroutineScope(SupervisorJob())

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope