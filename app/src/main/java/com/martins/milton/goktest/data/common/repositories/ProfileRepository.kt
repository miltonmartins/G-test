package com.martins.milton.goktest.data.common.repositories

import com.martins.milton.goktest.data.remote.models.AccountResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

interface ProfileRepository {
    suspend fun getAccount(): AccountResponse
}

class ProfileRepositoryFake @Inject constructor() : ProfileRepository {
    override suspend fun getAccount(): AccountResponse {
        delay(FAKE_DELAY_IN_MILLIS)

        return AccountResponse(name = "Maria")
    }

    companion object {
        const val FAKE_DELAY_IN_MILLIS = 2000L
    }
}