package com.project.cargoproj.repository

class ApiHelper(private val apiService: ApiInterface) {

     fun callAPI() = apiService.callAPI()
}