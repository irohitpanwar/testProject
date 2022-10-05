package com.project.cargoproj.repository


class MainRepository(private val apiHelper: ApiHelper) {

     fun callAPI( ) = apiHelper.callAPI()
}