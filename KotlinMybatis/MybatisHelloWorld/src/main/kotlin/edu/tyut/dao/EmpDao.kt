package edu.tyut.dao

import edu.tyut.bean.Emp

interface EmpDao {
    fun getEmpAndDept(empId: Int): Emp
    fun getEmpAndDeptByStepOne(empId: Int): Emp
    fun getDeptAndEmpByStepTwo(deptId: Int): List<Emp>
}