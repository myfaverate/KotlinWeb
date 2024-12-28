package edu.tyut.controller

import edu.tyut.annoation.InvokedByReflection
import edu.tyut.bean.Result
import edu.tyut.bean.SysSchedule
import edu.tyut.bean.SysUser
import edu.tyut.service.SysScheduleService
import edu.tyut.service.impl.SysScheduleServiceImpl
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * /上下文路径/schedule/add
 * 增 add
 * 删 remove
 * 改 update
 * 查 find
 */
@WebServlet(name = "SysScheduleController", value = ["/schedule/*"])
class SysScheduleController : Controller() {

    private val scheduleService: SysScheduleService = SysScheduleServiceImpl()
    @InvokedByReflection
    private fun findAllSchedule(request: HttpServletRequest?, response: HttpServletResponse?){
        val uid: Int = request?.getParameter("uid")?.toIntOrNull() ?: 1
        val dataList: List<SysSchedule> = scheduleService.findAllByUid(uid = uid)
        response?.writer?.write(Json.encodeToString(Result.success("获取数据成功", dataList)))
    }

    @InvokedByReflection
    private fun updateSchedule(request: HttpServletRequest?, response: HttpServletResponse?){
        val scheduleJson: String = request?.reader?.readText() ?: return
        logger.info("updateSchedule -> value: {}", scheduleJson)
        val sysSchedule: SysSchedule = Json.decodeFromString<SysSchedule>(scheduleJson)
        val rows: Int = scheduleService.updateSchedule(sysSchedule)
        if (rows > 0) {
            response?.writer?.write(Json.encodeToString(Result.success("修改成功", 0)))
        }else{
            response?.writer?.write(Json.encodeToString(Result.failure("修改失败", 0)))
        }
    }

    @InvokedByReflection
    private fun deleteSchedule(request: HttpServletRequest?, response: HttpServletResponse?){
        val sid: Int = request?.getParameter("sid")?.toIntOrNull() ?: 0
        val rows: Int = scheduleService.deleteSchedule(sid = sid)
        if (rows > 0) {
            response?.writer?.write(Json.encodeToString(Result.success("删除成功", 0)))
        }else{
            response?.writer?.write(Json.encodeToString(Result.failure("删除失败", 0)))
        }
    }


    @InvokedByReflection
    private fun addSchedule(request: HttpServletRequest?, response: HttpServletResponse?) {
        val scheduleJson: String = request?.reader?.readText() ?: return
        logger.info("addSchedule -> value: {}", scheduleJson)
        val sysSchedule: SysSchedule = Json.decodeFromString<SysSchedule>(scheduleJson)
        val rows: Int = scheduleService.addSchedule(sysSchedule)
        if (rows > 0) {
            response?.writer?.write(Json.encodeToString(Result.success("添加成功", 0)))
        }else{
            response?.writer?.write(Json.encodeToString(Result.failure("添加失败", 0)))
        }
    }

    @InvokedByReflection
    private fun add(request: HttpServletRequest?, response: HttpServletResponse?) {
        logger.info("SysScheduleController add...")
        logger.info("Request URL: ${request?.requestURL}")
        logger.info("Request URI: ${request?.requestURI}")

        coroutineScope.launch {
            logger.info("SysScheduleController -> 协程 out...")
            launch(context = Dispatchers.Default) {
                logger.info("SysScheduleController -> 协程 default...")
            }
            launch {
                logger.info("SysScheduleController -> 协程 无参数...")
            }
            launch(Dispatchers.IO) {
                delay(50000000)
                logger.info("SysScheduleController -> 协程 IO end ...")
            }.invokeOnCompletion {
                logger.info("completed....")
            }
            launch(Dispatchers.Unconfined) {
                logger.info("SysScheduleController -> 协程 Unconfined...")
            }
        }
    }

    @InvokedByReflection
    private fun remove(request: HttpServletRequest?, response: HttpServletResponse?) {
        logger.info("SysScheduleController remove...")
    }

    private fun update(request: HttpServletRequest?, response: HttpServletResponse?) {
        logger.info("SysScheduleController update...")
    }

    private fun find(request: HttpServletRequest?, response: HttpServletResponse?) {
        logger.info("SysScheduleController find...")
    }
}