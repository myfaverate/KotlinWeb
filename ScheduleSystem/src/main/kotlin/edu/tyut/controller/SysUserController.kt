package edu.tyut.controller

import ch.qos.logback.classic.encoder.JsonEncoder
import com.alibaba.druid.support.json.JSONUtils
import com.alibaba.druid.support.json.JSONWriter
import com.google.protobuf.DescriptorProtos.FeatureSet.JsonFormat
import com.mysql.cj.xdevapi.JsonArray
import edu.tyut.annoation.InvokedByReflection
import edu.tyut.bean.Result
import edu.tyut.bean.SysUser
import edu.tyut.service.SysUserService
import edu.tyut.service.impl.SysUserServiceImpl
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import netscape.javascript.JSObject

@WebServlet(name = "SysUserController", value = ["/user/*"])
class SysUserController : Controller(){

    private val userService: SysUserService = SysUserServiceImpl()

    @InvokedByReflection
    private fun register(request: HttpServletRequest?, response: HttpServletResponse?) {
        // === json 格式 ===
        val userJson: String = request?.reader?.readText() ?: return
        logger.info("register -> value: {}", userJson)
        val user: SysUser = Json.decodeFromString<SysUser>(userJson)
        logger.info("register -> user: $user")
        val rows: Int = userService.register(sysUser = user)
        if (rows > 0){
            response?.writer?.write(Json.encodeToString(Result.success("注册成功", 0)))
        }else{
            response?.writer?.write(Json.encodeToString(Result.failure("注册失败", 0)))
        }
        // === x-www-form-urlencoded 格式 ===
        // val username: String = request?.getParameter("username") ?: ""
        // val password: String = request?.getParameter("password") ?: ""
        // logger.info("register -> username: $username, password: $password")
        // val rows: Int = userService.register(sysUser = SysUser(uid = 0, username = username, password = password))
        // if (rows > 0){
        //     response?.sendRedirect("/static/page/registerSuccess.html")
        // }else{
        //     response?.sendRedirect("/static/page/registerFailure.html")
        // }
    }

    @InvokedByReflection
    private fun login(request: HttpServletRequest?, response: HttpServletResponse?) {
        // === json 格式 ===
        val userJson: String = request?.reader?.readText() ?: return
        logger.info("login -> value: {}", userJson)
        val user: SysUser = Json.decodeFromString<SysUser>(userJson)
        logger.info("login -> user: $user")
        when(val status: Int = userService.login(username = user.username, password = user.password)){
            0 -> {
                // 登陆成功
                response?.writer?.write(Json.encodeToString(Result.success(message = "登录成功", data = userService.findByUsername(username = user.username))))
                request.session?.setAttribute("user", user)
            }
            1 -> response?.writer?.write(Json.encodeToString(Result.failure("登录失败: 用户名错误", 0)))
            2 -> response?.writer?.write(Json.encodeToString(Result.failure("登录失败: 密码错误", 0)))
            else -> {
                response?.writer?.write(Json.encodeToString(Result.failure("登录失败: 其他错误: code: $status", 0)))
            }
        }
        // === x-www-form-urlencoded 格式 ===
        // val username: String = request?.getParameter("username") ?: ""
        // val password: String = request?.getParameter("password") ?: ""
        // logger.info("login -> username: $username, password: $password")
        // when(val status: Int = userService.login(username = username, password = password)){
        //     0 -> {
        //         // 登陆成功
        //         response?.sendRedirect("/static/page/showSchedule.html")
        //         request?.session?.setAttribute("username", username)
        //     }
        //     1 -> response?.sendRedirect("/static/page/loginUsernameError.html")
        //     2 -> response?.sendRedirect("/static/page/loginPasswordError.html")
        //     else -> {
        //         logger.info("其他错误 -> login -> status: $status")
        //     }
        // }
    }


    @InvokedByReflection
    private fun checkUsernameUsed(request: HttpServletRequest?, response: HttpServletResponse?) {
        val username: String = request?.getParameter("username") ?: ""
        response?.contentType = "application/json;charset=UTF-8"
        response?.writer?.println(
            if (userService.findByUsername(username = username) == null)
                Json.encodeToString(Result.success(message = "可用", data = 0))
            else Json.encodeToString(Result.failure(message = "已占用", data = 0))
        )
    }

    private fun remove(request: HttpServletRequest?, response: HttpServletResponse?) {
        logger.info("SysScheduleController remove...")
    }
}