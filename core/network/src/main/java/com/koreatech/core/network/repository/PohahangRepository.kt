package com.koreatech.core.network.repository

import com.koreatech.data.model.request.LoginRequestDto
import com.koreatech.data.model.request.SignupRequestDto
import com.koreatech.data.model.response.CatResponseDto
import kotlinx.coroutines.flow.Flow

interface PohahangRepository {
    suspend fun signup(signupRequestDto: SignupRequestDto)
    suspend fun login(loginRequestDto: LoginRequestDto)
    suspend fun getCats(): List<CatResponseDto>
}