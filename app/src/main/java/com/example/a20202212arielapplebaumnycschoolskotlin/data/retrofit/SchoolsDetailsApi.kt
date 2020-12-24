package com.example.a20202212arielapplebaumnycschoolskotlin.data.retrofit

import com.example.a20202212arielapplebaumnycschoolskotlin.data.School
import retrofit2.Call
import retrofit2.http.GET

interface SchoolsDetailsApi {

    @GET("?dbn= + dbn")
    fun getSchoolDetails(dbn : String) : Call<School>
}