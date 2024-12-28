package edu.tyut.dao

import edu.tyut.bean.Dept
import edu.tyut.bean.Emp
import edu.tyut.bean.User
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sun.security.jgss.GSSUtil.login
import java.io.InputStream
import kotlin.test.Test

class MybatisTest {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @Test
    fun testInsert(){
        Resources.getResourceAsStream("mybatis-config.xml").use{ inputStream: InputStream ->
            SqlSessionFactoryBuilder().build(inputStream).openSession().use { session: SqlSession ->
                val userDao: UserDao = session.getMapper(UserDao::class.java)
                val user = User(id = 0, username = "admin1", password = "111111", age = 18, gender = "男", email = "zsh@zsh.com")
                val rows: Int = userDao.insertUser(user)
                session.commit()
                if (rows > 0) {
                    logger.info("插入成功!")
                    logger.info("user with key: $user")
                }else{
                    logger.info("插入失败!")
                }
            }
        }
    }
    @Test
    fun testSelect(){
        Resources.getResourceAsStream("mybatis-config.xml").use{ inputStream: InputStream ->
            SqlSessionFactoryBuilder().build(inputStream).openSession().use { session: SqlSession ->
                val userDao: UserDao = session.getMapper(UserDao::class.java)
                val user: User? = userDao.getUserById(id = 2)
                logger.info("user: $user")
                val userList: List<User> = userDao.getAllUsers()
                logger.info("userList: $userList")
                val loginUser: User? = userDao.login(username = user?.username ?: "", password = user?.password ?: "", age = user?.age ?: 0)
                logger.info("loginUser: $loginUser")
                val count: Int = userDao.getCount()
                logger.info("count: $count")
                val usersByLike: List<User> = userDao.getUsersByLike(usernameLike = "a")
                logger.info("usersByLike: $usersByLike")
            }
        }
    }
    @Test
    fun testOneMoreSelect(){
        Resources.getResourceAsStream("mybatis-config.xml").use{ inputStream: InputStream ->
            SqlSessionFactoryBuilder().build(inputStream).openSession().use { session: SqlSession ->
                val empDao: EmpDao = session.getMapper(EmpDao::class.java)
                val emp: Emp = empDao.getEmpAndDept(empId = 1)
                logger.info("emp: $emp")
            }
        }
    }
    @Test
    fun testGetEmpAndDeptByStepSelect(){
        Resources.getResourceAsStream("mybatis-config.xml").use{ inputStream: InputStream ->
            SqlSessionFactoryBuilder().build(inputStream).openSession().use { session: SqlSession ->
                val empDao: EmpDao = session.getMapper(EmpDao::class.java)
                val emp: Emp = empDao.getEmpAndDeptByStepOne(empId = 1)
                logger.info("testGetEmpAndDeptByStepSelect -> emp: ${emp.age}")
            }
        }
    }
    @Test
    fun testGetDeptAndEmpByDept(){
        Resources.getResourceAsStream("mybatis-config.xml").use{ inputStream: InputStream ->
            SqlSessionFactoryBuilder().build(inputStream).openSession().use { session: SqlSession ->
                val deptDao: DeptDao = session.getMapper(DeptDao::class.java)
                val dept: Dept = deptDao.getDeptAndEmpByDeptId(deptId = 1)
                logger.info("testGetDeptAndEmpByDeptId -> dept: $dept")
                val deptOne: Dept = deptDao.getDeptAndEmpByByStepOne(deptId = 1)
                logger.info("testGetDeptAndEmpByDeptId -> deptOne: ${deptOne.deptName}")
            }
        }
    }
}