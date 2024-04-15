package com.koreatech.core.network.di

import android.util.Log
import com.koreatech.core.network.RetrofitNetwork
import com.koreatech.core.network.repository.PohahangRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesAuthRepository(
        networkJson: Json,
        okHttpCallFactory: dagger.Lazy<Call.Factory>,
    ): PohahangRepository {
        return RetrofitNetwork(
            networkJson, okHttpCallFactory
        )
    }
}
