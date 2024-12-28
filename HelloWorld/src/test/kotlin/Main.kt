import org.dom4j.Attribute
import org.dom4j.Document
import org.dom4j.Element
import org.dom4j.io.SAXReader
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.charset.Charset
import java.util.*
import java.util.jar.Attributes
import kotlin.test.Test

class Main {
    @Test
    fun read(){
        val inputStream: InputStream? = Main::class.java.classLoader.getResourceAsStream("jdbc.xml")
        inputStream?.use { input: InputStream ->
            val bytes = ByteArray(1024)
            var len: Int
            while (input.read(bytes).also { len = it } != -1){
                println(String(bytes, 0, len, Charsets.UTF_8))
            }
        }
        println("==============")
        (Main::class.java.classLoader.getResource("jdbc.xml")?.content as? InputStream)?.use { input: InputStream ->
            val bytes = ByteArray(1024)
            var len: Int
            while (input.read(bytes).also { len = it } != -1){
                println(String(bytes, 0, len, Charsets.UTF_8))
            }
        }
        println("==================")
        val saxReader = SAXReader()
        val document: Document = saxReader.read(Main::class.java.classLoader.getResourceAsStream("jdbc.xml"))
        // 获取根标签
        val rootElement: Element = document.rootElement
        println("rootElement: ${rootElement.name}")
        val elements: MutableList<Element> = rootElement.elements()
        elements.forEach { element: Element ->
            println("element: ${element.name}")
            // 获取属性
            val attribute: Attribute = element.attribute("id")
            println("attribute -> name: ${attribute.name}, value: ${attribute.value}")
            val subElements: MutableList<Element> = element.elements()
            subElements.forEach { subElement: Element ->
                println("subElement: name: ${subElement.name}, text: ${subElement.text}")
            }
        }
    }

    @Test
    fun readProperties(){
        val properties = Properties()
        properties.load(Main::class.java.classLoader.getResourceAsStream("jdbc.properties"))
        properties.forEach { key, value -> println("$key: $value") }
        properties.storeToXML(FileOutputStream("src/main/resources/jdbc2.xml"), "jdbc2")
    }
}