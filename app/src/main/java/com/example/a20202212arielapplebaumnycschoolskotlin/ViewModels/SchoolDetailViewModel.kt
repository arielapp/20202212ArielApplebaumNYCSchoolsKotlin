package com.example.a20202212arielapplebaumnycschoolskotlin.ViewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.a20202212arielapplebaumnycschoolskotlin.data.School
import com.example.a20202212arielapplebaumnycschoolskotlin.data.SchoolsDao
import com.example.a20202212arielapplebaumnycschoolskotlin.data.retrofit.SchoolsDetailsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class SchoolDetailViewModel @ViewModelInject constructor(
    private val schoolDao: SchoolsDao
) : ViewModel() {

    lateinit var schoolName: String

    fun getDetails(dbn: String) : School?{

        var schoolDetails: School? = School("","","","","","","")

        val api = Retrofit.Builder()
            .baseUrl("https://data.cityofnewyork.us/resource/f9bf-2cp4.json")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SchoolsDetailsApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {

            try {

                val response = api.getSchoolDetails(dbn).awaitResponse()

                if (response.isSuccessful) {
                    schoolDetails = response.body()
                } else {
                    schoolDetails = schoolDao.getSchoolDetail(schoolName).value
                }

            } catch (e: Exception) {

            }

        }

        return schoolDetails

    }

    fun setSchoolDetailName(name: String) {
        schoolName = name
    }
}