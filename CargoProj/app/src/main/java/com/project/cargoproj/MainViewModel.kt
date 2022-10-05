package com.project.cargoproj

import com.project.cargoproj.repository.MainRepository
import androidx.lifecycle.ViewModel
import com.project.cargoproj.model.ResponseModel
import org.json.JSONObject
import retrofit2.Call


class MainViewModel (private val mainRepository: MainRepository) : ViewModel() {

     fun getImage(): Call<JSONObject> {
         return mainRepository.callAPI()
     }
}




