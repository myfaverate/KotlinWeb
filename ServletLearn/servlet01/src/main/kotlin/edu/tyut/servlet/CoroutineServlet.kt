package edu.tyut.servlet

import edu.tyut.utils.Constants
import jakarta.servlet.AsyncContext
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory

internal abstract class CoroutineServlet internal constructor() : HttpServlet() {

    private val logger: Logger = LoggerFactory.getLogger(CoroutineServlet::class.java)

    override fun doGet(request: HttpServletRequest?, response: HttpServletResponse?) {

        if (request == null || response == null) {
            logger.warn("request == null || response == null ...")
            return
        }

        if (!request.isAsyncSupported) {
            logger.warn("request is sync not supported")
            throw IllegalStateException("request is sync not supported")
        }

        val asyncContext: AsyncContext = request.startAsync()
        Constants.scope.launch {
            try {
                logger.info("Started async request...")
                this@CoroutineServlet.handle(
                    request = request,
                    response = response,
                )
            } catch (e: Exception) {
                logger.error("error: ${e.message}", e)
            } finally {
                asyncContext.complete()
            }
        }
    }

    override fun doPost(request: HttpServletRequest?, response: HttpServletResponse?) {
        doGet(request = request, response = response)
    }

    // 子类实现的 suspend 函数
    protected abstract suspend fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse
    )
}
