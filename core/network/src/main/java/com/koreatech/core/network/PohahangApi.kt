package com.koreatech.core.network

import com.koreatech.data.model.request.LoginRequestDto
import com.koreatech.data.model.request.SignupRequestDto
import com.koreatech.data.model.response.CatResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PohahangApi {
    @POST("/api/v1/members")
    suspend fun signup(
        @Body signupRequestDto: SignupRequestDto
    ): NetworkResponse<Unit>

    @POST("/api/v1/members/sign-in")
    suspend fun login(
        @Body loginRequestDto: LoginRequestDto
    ): NetworkResponse<Unit>

    @GET("/v1/images/search")
    suspend fun getCats(
        @Query("limit") limit: Int
    ): List<CatResponseDto>
}