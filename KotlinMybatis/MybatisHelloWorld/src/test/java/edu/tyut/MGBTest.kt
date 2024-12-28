package edu.tyut

import edu.tyut.bean.Emp
import edu.tyut.bean.NewEmp
import edu.tyut.bean.NewEmpExample
import edu.tyut.dao.EmpDao
import edu.tyut.dao.NewEmpMapper
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.InputStream
import kotlin.test.Test

class MGBTest {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Test
    fun testMGB() {
        Resources.getResourceAsStream("mybatis-config.xml").use { inputStream: InputStream ->
            SqlSessionFactoryBuilder().build(inputStream).openSession().use { session: SqlSession ->
                val newEmpMapper: NewEmpMapper = session.getMapper(NewEmpMapper::class.java)
                val example = NewEmpExample()
                example.createCriteria().andAgeGreaterThanOrEqualTo(18)
                val emp: List<NewEmp>? = newEmpMapper.selectByExample(example)
                logger.info("testGetEmpAndDeptByStepSelect -> emp: ${emp}")
            }
        }
    }
}