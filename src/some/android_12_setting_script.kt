package some

import org.w3c.dom.Document
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

private const val path = "C:\\Users\\weeih\\Projects2\\Work\\app\\android\\app\\src\\main\\AndroidManifest.xml";

private fun main() {
    val xlmFile: File = File(path)
    val xmlDoc: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xlmFile)

    fun processElements(elementType: String){
        val elements = xmlDoc.getElementsByTagName(elementType)
        for (i in 0 until elements.length) {
            val element = elements.item(i)
            val elementName = element.attributes.getNamedItem("android:name")
            val elementExported = element.attributes.getNamedItem("android:exported")
            val childs = element.childNodes
            if(childs.length>0){
                for (j in 0 until childs.length) {
                    val childNode = childs.item(j)
                    if(childNode.nodeName=="intent-filter")
                        println("${elementExported?.nodeValue?.let{"✓"}?:"X"} <$elementType> ${elementName.nodeValue}")
                }
            }

        }
    }

    println("Root Node:" + xmlDoc.documentElement.nodeName)
    processElements("activity")
    processElements("service")
    processElements("receiver")
}