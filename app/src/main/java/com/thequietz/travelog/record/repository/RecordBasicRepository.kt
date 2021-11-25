package com.thequietz.travelog.record.repository

import com.thequietz.travelog.data.db.dao.RecordImageDao
import com.thequietz.travelog.data.db.dao.ScheduleDao
import com.thequietz.travelog.data.db.dao.ScheduleDetailDao
import com.thequietz.travelog.record.model.RecordImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecordBasicRepository @Inject constructor(
    private val scheduleDao: ScheduleDao,
    private val scheduleDetailDao: ScheduleDetailDao,
    private val recordImageDao: RecordImageDao,
    private val coroutineScope: CoroutineScope
) {
    fun loadAllSchedule() = scheduleDao.loadAllSchedule()

    fun loadScheduleDetailOrderByScheduleIdAndDate(scheduleId: Int) =
        scheduleDetailDao.loadScheduleDetailsByScheduleIdOrderByScheduleIdAndDate(scheduleId)

    fun loadRecordImagesByTravelId(travelId: Int) =
        recordImageDao.loadRecordImageByTravelId(travelId)

    fun insertRecordImage(image: RecordImage) {
        coroutineScope.launch { recordImageDao.insert(image) }
    }

    fun insertRecordImages(images: List<RecordImage>) {
        coroutineScope.launch { recordImageDao.insert(*images.toTypedArray()) }
    }

    fun deleteRecordImageByPlace(place: String) {
        coroutineScope.launch {
            recordImageDao.deleteRecordImageByPlace(place)
        }
    }
}