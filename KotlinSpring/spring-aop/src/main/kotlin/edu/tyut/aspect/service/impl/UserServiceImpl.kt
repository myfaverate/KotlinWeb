package edu.tyut.aspect.service.impl

import edu.tyut.aspect.dao.UserDao
import edu.tyut.aspect.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userDao: UserDao,
) : UserService {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun saveUser() {
        logger.info("userDao: $userDao")
        userDao.saveUser()
    }
}