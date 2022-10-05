package com.project.cargoproj.repository

class ApiHelper(private val apiService: ApiInterface) {

     fun callAPI( limit : Int?, page: Int?,  order: String?) = apiService.callAPI( limit ,  page,  order)
}