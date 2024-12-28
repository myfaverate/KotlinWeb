package edu.tyut.dao

import edu.tyut.bean.Dept


interface DeptDao {
    fun getEmpAndDeptByStepTwo(deptId: Int): Dept
    fun getDeptAndEmpByDeptId(deptId: Int): Dept
    fun getDeptAndEmpByByStepOne(deptId: Int): Dept
}