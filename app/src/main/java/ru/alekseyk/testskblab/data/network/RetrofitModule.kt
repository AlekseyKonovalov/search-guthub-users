package ru.alekseyk.testskblab.data.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

private const val GITHUB_API_URL = "https://api.github.com"

val retrofitModule = module {

    single { createApiService<Api>(get(), GITHUB_API_URL) }

    factory { createLoggingInterceptor() }

    factory { createOkHttpClient(get(), get()) }

    factory { createNetworkInterceptor() }
}

fun createLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
        Timber.d(message)
    })
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

fun createNetworkInterceptor(): Interceptor {
    return Interceptor {
        val request = it.request().newBuilder()
            .build()
        it.proceed(request)
    }
}

fun createOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    interceptor: Interceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(interceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .retryOnConnectionFailure(false)
        .build()
}

inline fun <reified T> createApiService(okHttpClient: OkHttpClient, apiUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}