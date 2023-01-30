package com.example.custompaged.Data.Repository

import com.example.custompaged.Data.Remote.Api
import com.example.custompaged.Data.Remote.Users
import com.example.custompaged.Data.Remote.Usersrespo
import retrofit2.Response
import javax.inject.Inject

class Repository (val api: Api) : Api {

    override suspend fun users(page : Int): Response<Usersrespo> {
        return api.users(page)
    }
}