package com.project.cargoproj.repository

import com.project.cargoproj.model.ListResponse
import com.project.cargoproj.model.ResponseModel
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    //?limit=100&page=10&order=Desc
    @GET("images/search?limit=100&page=10&order=Desc")
    fun callAPI(
    ): Call<JSONObject>
}