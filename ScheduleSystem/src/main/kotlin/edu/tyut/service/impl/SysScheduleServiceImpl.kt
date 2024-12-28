package edu.tyut.service.impl

import edu.tyut.bean.SysSchedule
import edu.tyut.dao.SysScheduleDao
import edu.tyut.dao.impl.SysScheduleDaoImpl
import edu.tyut.service.SysScheduleService

class SysScheduleServiceImpl : SysScheduleService {
    private val scheduleDao: SysScheduleDao = SysScheduleDaoImpl()
    override fun findAllByUid(uid: Int): List<SysSchedule> {
        return scheduleDao.findAllByUid(uid)
    }

    override fun addSchedule(schedule: SysSchedule): Int {
        return scheduleDao.addSchedule(schedule)
    }

    override fun updateSchedule(schedule: SysSchedule): Int {
        return scheduleDao.updateSchedule(schedule)
    }

    override fun deleteSchedule(sid: Int): Int {
        return scheduleDao.deleteSchedule(sid = sid)
    }


}