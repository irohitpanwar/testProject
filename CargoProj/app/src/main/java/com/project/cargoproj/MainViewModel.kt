package com.project.cargoproj

import com.project.cargoproj.repository.MainRepository
import androidx.lifecycle.ViewModel
import com.project.cargoproj.model.ResponseModel
import org.json.JSONObject
import retrofit2.Call


class MainViewModel (private val mainRepository: MainRepository) : ViewModel() {

    fun getImage( limit : Int?=15, page: Int?,  order: String?="Desc"): Call<List<ResponseModel>?>? {
        return mainRepository.callAPI( limit ,  page,  order)
    }
}




