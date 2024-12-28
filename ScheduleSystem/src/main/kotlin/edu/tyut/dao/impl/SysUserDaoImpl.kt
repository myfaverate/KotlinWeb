package edu.tyut.dao.impl

import edu.tyut.bean.SysUser
import edu.tyut.dao.SysUserDao
import edu.tyut.util.DaoUtil

class SysUserDaoImpl : SysUserDao {
    override fun addSysUser(sysUser: SysUser) : Int {
        val sql = "insert into sys_user values(default, ?, ?)"
        return DaoUtil.executeUpdate(sql, sysUser.username, sysUser.password)
    }

    override fun findByUsername(username: String): SysUser? {
        val sql = "select * from sys_user where username = ?"
        return DaoUtil.executeQuery(clazz = SysUser::class.java, sql = sql, username).firstOrNull()
    }
}