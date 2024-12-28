package edu.tyut.service

import edu.tyut.bean.SysUser

/**
 * 该接口定义了以 sys_user 表格为核心的业务处理功能
 *
 */
interface SysUserService {

    /**
     * 注册用户
     */
    fun register(sysUser: SysUser): Int
    fun login(username: String, password: String): Int
    fun findByUsername(username: String): SysUser?
}