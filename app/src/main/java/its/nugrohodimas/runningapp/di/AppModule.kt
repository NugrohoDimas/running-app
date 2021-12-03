package its.nugrohodimas.runningapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import its.nugrohodimas.runningapp.db.RunningDatabase
import its.nugrohodimas.runningapp.helper.Constants.DATABASE_NAME
import its.nugrohodimas.runningapp.helper.Constants.KEY_FIRST_TIME_TOOGLE
import its.nugrohodimas.runningapp.helper.Constants.KEY_NAME
import its.nugrohodimas.runningapp.helper.Constants.KEY_WEIGHT
import its.nugrohodimas.runningapp.helper.Constants.SHARED_PREFERENCES_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabse(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RunningDatabase::class.java,
        DATABASE_NAME
    ).build()


    @Singleton
    @Provides
    fun provideRunDao(
        db: RunningDatabase
    ) = db.runDao()

    @Singleton
    @Provides
    fun provideName(sharedPref: SharedPreferences) = sharedPref.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPref: SharedPreferences) = sharedPref.getFloat(KEY_WEIGHT, 53f)

    @Singleton
    @Provides
    fun provideFirstTimeOpen(sharedPref: SharedPreferences) =
        sharedPref.getBoolean(KEY_FIRST_TIME_TOOGLE, true)

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context) = app.getSharedPreferences(
        SHARED_PREFERENCES_NAME, MODE_PRIVATE)
}