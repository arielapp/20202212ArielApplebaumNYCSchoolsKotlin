package com.example.a20202212arielapplebaumnycschoolskotlin.data.retrofit

import com.example.a20202212arielapplebaumnycschoolskotlin.data.School
import retrofit2.Call
import retrofit2.http.GET

interface SchoolsApi {


    @GET("https://data.cityofnewyork.us/resource/s3k6-pzi2.json")
    fun getSchoolsList() : Call<List<School>>


}