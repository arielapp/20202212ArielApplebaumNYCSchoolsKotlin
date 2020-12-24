package com.example.a20202212arielapplebaumnycschoolskotlin.ViewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20202212arielapplebaumnycschoolskotlin.data.School
import com.example.a20202212arielapplebaumnycschoolskotlin.data.SchoolsDao
import com.example.a20202212arielapplebaumnycschoolskotlin.data.retrofit.SchoolsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class SchoolsViewModel @ViewModelInject constructor(
    private val schoolsDao: SchoolsDao
) : ViewModel() {

    private val schoolsEventsChannel = Channel<SchoolsEvents>()

    fun getData(): List<School>? {
        var schools: List<School>? = listOf()
        val api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SchoolsApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {

            try {

                val response = api.getSchoolsList().awaitResponse()

                if (response.isSuccessful) {
                    schools = response.body()!!
                } else {
                    schools = schoolsDao.getSchools().value
                }

            } catch (e: Exception) {

            }

        }
        return schools
    }


    //
    val schoolsEvents = schoolsEventsChannel.receiveAsFlow()

    fun onItemClicked(school: School) = viewModelScope.launch {
        schoolsEventsChannel.send(SchoolsEvents.NavigateToDetails(school))
    }

    sealed class SchoolsEvents {
        data class NavigateToDetails(val school: School) : SchoolsEvents()
    }


}