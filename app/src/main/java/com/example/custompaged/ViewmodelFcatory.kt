package com.example.custompaged

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.custompaged.Data.Repository.Repository

class viewmodelFctory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Mainviewmodel::class.java)){
            return Mainviewmodel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Viewmodel not found")
    }


}
