package com.project.cargoproj

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.cargoproj.repository.ApiHelper
import com.project.cargoproj.repository.MainRepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

