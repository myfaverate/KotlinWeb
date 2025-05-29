package edu.tyut.dating.controller

import edu.tyut.dating.bean.AuthCodeBean
import edu.tyut.dating.bean.CodePhoneBean
import edu.tyut.dating.bean.PhoneRequest
import edu.tyut.dating.bean.Result
import edu.tyut.dating.bean.UserInfoStateBean
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController(value = "LoginController")
@RequestMapping(value = ["/login"])
internal class LoginController {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping(value = ["/getCode"])
    internal fun getCode(
        @RequestBody phoneRequest: PhoneRequest,
        @RequestHeader headers: Map<String, String>
    ): Result {
        logger.info("phoneRequest: $phoneRequest, headers: $headers")
        return Result.success(message = "成功", data = AuthCodeBean(code = "888888")).apply {
            logger.info("result: $this")
        }
    }

    @PostMapping(value = ["/loginVerification"])
    internal fun loginVerification(
        @RequestBody codePhoneBean: CodePhoneBean,
        @RequestHeader headers: Map<String, String>
    ): Result {
        logger.info("loginVerification: $codePhoneBean, headers: $headers")
        return Result.success(message = "成功", data = UserInfoStateBean(isNew = true))
    }

    @PostMapping(value = ["/register"])
    internal fun register(
        @RequestBody codePhoneBean: CodePhoneBean,
        @RequestHeader headers: Map<String, String>
    ): Result {
        logger.info("loginVerification: $codePhoneBean, headers: $headers")
        return Result.success(message = "成功", data = UserInfoStateBean(isNew = true))
    }
}