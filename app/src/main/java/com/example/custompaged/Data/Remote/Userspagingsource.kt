package com.example.custompaged.Data.Remote

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.custompaged.Data.Repository.Repository
import javax.inject.Inject

class Userspagingsource (val  repository: Repository) : PagingSource<Int,Users>() {
    override fun getRefreshKey(state: PagingState<Int, Users>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Users> {

        return try {
            var  currentpage  = params.key ?: 1
            var userspage  = repository.users(currentpage)
            var users  = userspage.body()?.user ?: emptyList()
            var data = mutableListOf<Users>()
            data.addAll(users)
            Log.d("users",data.toString())
            LoadResult.Page(
                data = data,
                nextKey = currentpage.plus(1),
                prevKey = if (currentpage==1) null else -1
            )

        } catch (e : Exception){
            LoadResult.Error(e)
        }

    }

}