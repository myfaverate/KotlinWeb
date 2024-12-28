package edu.tyut.service.impl

import edu.tyut.bean.SysUser
import edu.tyut.dao.SysUserDao
import edu.tyut.dao.impl.SysUserDaoImpl
import edu.tyut.service.SysUserService
import edu.tyut.util.Utils

class SysUserServiceImpl : SysUserService {

    private val sysUserDao: SysUserDao = SysUserDaoImpl()

    override fun register(sysUser: SysUser): Int {
        // MD5 加密
        sysUser.password = Utils.toMD5(content = sysUser.password)
        // 存入数据库
        return sysUserDao.addSysUser(sysUser = sysUser)
    }

    override fun login(username: String, password: String): Int {
        val sysUser: SysUser = sysUserDao.findByUsername(username = username) ?: return 1
        if (Utils.toMD5(content = password) == sysUser.password){
            return 0
        }
        return 2
    }

    override fun findByUsername(username: String): SysUser? {
        return sysUserDao.findByUsername(username)
    }
}