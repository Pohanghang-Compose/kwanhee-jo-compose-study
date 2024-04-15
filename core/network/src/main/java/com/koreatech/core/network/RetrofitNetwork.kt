package com.koreatech.core.network

import com.koreatech.core.network.repository.PohahangRepository
import com.koreatech.data.model.request.LoginRequestDto
import com.koreatech.data.model.request.SignupRequestDto
import com.koreatech.data.model.response.CatResponseDto
import kotlinx.serialization.json.Json
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RetrofitNetwork @Inject constructor(
    networkJson: Json,
    okHttpCallFactory: dagger.Lazy<Call.Factory>,
) : PohahangRepository {
    private val retrofitApi = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/")
        .callFactory { okHttpCallFactory.get().newCall(it) }
//        .addConverterFactory(
//            networkJson.asConverterFactory("application/json".toMediaType())
//        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PohahangApi::class.java)

    override suspend fun signup(signupRequestDto: SignupRequestDto) {
        retrofitApi.signup(signupRequestDto)
    }

    override suspend fun login(loginRequestDto: LoginRequestDto) {
        retrofitApi.login(loginRequestDto)
    }

    override suspend fun getCats(): List<CatResponseDto> =
        retrofitApi.getCats(100)

}