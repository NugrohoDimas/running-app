package its.nugrohodimas.runningapp.repository

import its.nugrohodimas.runningapp.db.Run
import its.nugrohodimas.runningapp.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(val runDAO: RunDAO) {
    suspend fun insertRun(run: Run) = runDAO.insertRun(run)

    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)

    fun getAllRunByDate() = runDAO.getAllRunsByDate()

    fun getAllRunByDistance() = runDAO.getAllRunsByDistanceInMeters()

    fun getAllRunByTimeInMillis() = runDAO.getAllRunsByTimeMillis()

    fun getAllRunByAverageSpeed() = runDAO.getAllRunsByAverageSpeed()

    fun getTotalAverageSpeed() = runDAO.getTotalAverageSpeed()

    fun getTotalTimeMillis() = runDAO.getTotalTimeMillis()

    fun getTotalDistance() = runDAO.getTotalDistanceInMeters()

    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurned()

}