package ir.romina.reza.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.romina.reza.core.util.NetworkObserver
import ir.romina.reza.feature_map_screen.data.local.StationsDatabase
import ir.romina.reza.feature_map_screen.data.remote.StationsApi
import ir.romina.reza.feature_map_screen.data.repository.StationRepositoryImpl
import ir.romina.reza.feature_map_screen.domain.repository.StationRepository
import ir.romina.reza.feature_map_screen.domain.use_case.GetStationsUseCase
import ir.romina.reza.feature_station_detail.domain.use_case.GetStationDataUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideStationsApi(client: OkHttpClient): StationsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(StationsApi.BASE_URL)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): StationsDatabase {
        return Room.databaseBuilder(
            app,
            StationsDatabase::class.java,
            StationsDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideStationRepository(api: StationsApi, db: StationsDatabase): StationRepository {
        return StationRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideNetworkObserver(@ApplicationContext context: Context): NetworkObserver {
        return NetworkObserver(context)
    }

    @Provides
    @Singleton
    fun provideGetStationsUseCase(repository: StationRepository): GetStationsUseCase {
        return GetStationsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetStationUseCase(repository: StationRepository): GetStationDataUseCase {
        return GetStationDataUseCase(repository)
    }

}