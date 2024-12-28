package edu.tyut.service

import edu.tyut.bean.SysSchedule

interface SysScheduleService {
    fun findAllByUid(uid: Int): List<SysSchedule>
    fun addSchedule(schedule: SysSchedule): Int
    fun updateSchedule(schedule: SysSchedule): Int
    fun deleteSchedule(sid: Int): Int
}