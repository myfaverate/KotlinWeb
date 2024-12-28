package edu.tyut.dao.impl

import edu.tyut.bean.SysSchedule
import edu.tyut.dao.SysScheduleDao
import edu.tyut.util.DaoUtil

class SysScheduleDaoImpl : SysScheduleDao {

    override fun addSchedule(schedule: SysSchedule): Int {
        val sql = "insert into sys_schedule values (default, ?, ?, ?)"
        val rows: Int = DaoUtil.executeUpdate(sql, schedule.uid, schedule.title, schedule.completed)
        return rows
    }

    override fun updateSchedule(schedule: SysSchedule): Int {
        val sql = "update sys_schedule set title=?, completed = ? where sid = ?"
        return DaoUtil.executeUpdate(sql, schedule.title, schedule.completed, schedule.sid)
    }

    override fun deleteSchedule(sid: Int): Int {
        val sql = "delete from sys_schedule where sid = ?"
        return DaoUtil.executeUpdate(sql, sid)
    }

    override fun findAll(): List<SysSchedule> {
        return DaoUtil.executeQuery(SysSchedule::class.java, "SELECT * FROM sys_schedule")
    }

    override fun findAllByUid(uid: Int): List<SysSchedule> {
        return DaoUtil.executeQuery(SysSchedule::class.java, "SELECT * FROM sys_schedule where uid = ?", uid)
    }

}