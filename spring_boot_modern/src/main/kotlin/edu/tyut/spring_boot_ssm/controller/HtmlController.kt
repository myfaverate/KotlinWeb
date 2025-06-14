package edu.tyut.spring_boot_ssm.controller

import kotlinx.coroutines.coroutineScope
import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.w3c.dom.Document

@RequestMapping(value = ["/html"])
@Controller
private final class HtmlController {

    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @ResponseBody
    @GetMapping(value = ["/hello"])
    private final suspend fun hello(
        @RequestParam(value = "message", required = false) message: String?,
    ): String = coroutineScope {
        logger.info("html hello...")
        val document: Document = createHTMLDocument().html {
            head {
                title { +"Hello Title" }
                script(type = ScriptType.textJavaScript) {
                    unsafe {
                        raw(
                            s = """
                            function hello() {
                                console.log('Hello world')
                                document.querySelector('.p').style.color = 'blue'
                            }
                        """.trimIndent()
                        )
                    }
                }
                style {
                    unsafe {
                        raw(
                            s = """
                                .p{
                                    color: red
                                }
                                """.trimIndent()
                        )
                    }
                }
            }
            body {
                h2(classes = "p") {
                    attributes["onclick"] = "hello()"
                    +"Hello Body Click me!"
                    +"message: $message"
                }
            }
        }
        document.serialize()
    }

    @GetMapping(value = ["/thymeleaf"])
    private final suspend fun thymeleaf(
        @RequestParam(value = "message", required = false) message: String?,
        model: Model
    ): String = coroutineScope {
        model.addAttribute("message", message)
        return@coroutineScope "index"
    }
}