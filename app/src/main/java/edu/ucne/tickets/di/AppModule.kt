package edu.ucne.tickets.di

import javax.inject.Singleton
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import edu.ucne.tickets.data.remote.EventApi

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(moshi: Moshi): EventApi {
        val Base_Url = "https://localhost:3001/"
        return Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(EventApi::class.java)
    }
}