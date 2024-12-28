package edu.tyut.dao

import edu.tyut.bean.SysSchedule

/**
 * dao
 */
interface SysScheduleDao {

    /**
     * @param schedule
     * @return
     */
    fun addSchedule(schedule: SysSchedule): Int

    fun updateSchedule(schedule: SysSchedule): Int

    fun deleteSchedule(sid: Int): Int

    /**
     * 查询所有用户的所有日程
     * @return 将所有日程放入一个List<SysSchedule> 集合中返回
     */
    fun findAll(): List<SysSchedule>

    fun findAllByUid(uid: Int): List<SysSchedule>
}