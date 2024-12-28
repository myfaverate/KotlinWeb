package edu.tyut.dao

import edu.tyut.bean.SysSchedule
import edu.tyut.dao.impl.SysScheduleDaoImpl
import kotlin.test.Test


class DaoTest {

    @Test
    fun querySchedule(){
        val dao = SysScheduleDaoImpl()
        println(dao.addSchedule(SysSchedule(sid = 0, uid = 2, title = "学习数据库", completed = 0)))
        println(dao.findAll())
        println("querySchedule...")
    }
}