package edu.tyut.bean;

public class NewDept {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept.dept_id
     *
     * @mbg.generated Wed Dec 04 22:48:01 CST 2024
     */
    private Integer deptId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept.dept_name
     *
     * @mbg.generated Wed Dec 04 22:48:01 CST 2024
     */
    private String deptName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept.dept_id
     *
     * @return the value of dept.dept_id
     *
     * @mbg.generated Wed Dec 04 22:48:01 CST 2024
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept.dept_id
     *
     * @param deptId the value for dept.dept_id
     *
     * @mbg.generated Wed Dec 04 22:48:01 CST 2024
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept.dept_name
     *
     * @return the value of dept.dept_name
     *
     * @mbg.generated Wed Dec 04 22:48:01 CST 2024
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept.dept_name
     *
     * @param deptName the value for dept.dept_name
     *
     * @mbg.generated Wed Dec 04 22:48:01 CST 2024
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
}