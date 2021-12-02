package its.nugrohodimas.runningapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RunDAO {
    @Insert
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC")
    fun getAllRunsByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY timeMillis DESC")
    fun getAllRunsByTimeMillis(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY caloriesBurned DESC")
    fun getAllRunsByCaloriesBurned(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY distanceInMeters DESC")
    fun getAllRunsByDistanceInMeters(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY averageSpeed DESC")
    fun getAllRunsByAverageSpeed(): LiveData<List<Run>>

    @Query("SELECT SUM(timeMillis) FROM running_table")
    fun getTotalTimeMillis(): LiveData<Long>

    @Query("SELECT SUM(distanceInMeters) FROM running_table")
    fun getTotalDistanceInMeters(): LiveData<Int>

    @Query("SELECT SUM(caloriesBurned) FROM running_table")
    fun getTotalCaloriesBurned(): LiveData<Int>

    @Query("SELECT AVG(averageSpeed) FROM running_table")
    fun getTotalAverageSpeed(): LiveData<Float>


}