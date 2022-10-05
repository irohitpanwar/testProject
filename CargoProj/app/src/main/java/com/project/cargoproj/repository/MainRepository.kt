package com.project.cargoproj.repository


class MainRepository(private val apiHelper: ApiHelper) {

     fun callAPI( limit : Int?, page: Int?,  order: String?) = apiHelper.callAPI( limit ,  page,  order)
}