package edu.tyut.aspect.dao.impl

import edu.tyut.aspect.dao.UserDao
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class UserDaoImpl : UserDao {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun saveUser() {
        logger.info("Saving user...")
    }
}