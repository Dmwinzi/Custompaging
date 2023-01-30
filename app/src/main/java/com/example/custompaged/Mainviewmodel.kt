package com.example.custompaged

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.custompaged.Data.Remote.Userspagingsource
import com.example.custompaged.Data.Repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class Mainviewmodel(val repository: Repository) : ViewModel() {

      val user = Pager(PagingConfig(pageSize = 1)){
          Userspagingsource(repository)
      }.flow.cachedIn(viewModelScope)



}