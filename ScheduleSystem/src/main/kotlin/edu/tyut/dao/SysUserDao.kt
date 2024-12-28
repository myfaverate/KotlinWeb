package edu.tyut.dao

import edu.tyut.bean.SysUser

interface SysUserDao {
    /**
     * 向数据库中添加用户
     */
    fun addSysUser(sysUser: SysUser): Int
    fun findByUsername(username: String): SysUser?
}