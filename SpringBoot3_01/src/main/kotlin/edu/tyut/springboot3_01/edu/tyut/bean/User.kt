package edu.tyut.springboot3_01.edu.tyut.bean

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import org.springframework.boot.context.properties.ConfigurationProperties
import java.io.Serializable

@ConfigurationProperties(prefix = "user")
@JacksonXmlRootElement(localName = "User")
// @Component
data class User(
    var username: String = "",
    var password: String = "",
    var age: Int = 0,
    var salary: Int = 0,
)
