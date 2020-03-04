package me.gogosing.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import me.gogosing.model.filter.FilterDateRange
import org.slf4j.LoggerFactory

/**
 * Created by JinBum Jeong on 2020/03/04.
 */
class PagingFilterDeserializer : StdDeserializer<Map<String, Any>>(Map::class.java) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun deserialize(parser: JsonParser, context: DeserializationContext): Map<String, Any> {
        val rootNode = parser.codec.readTree<JsonNode>(parser)
        val filters = mutableMapOf<String, Any>()

        rootNode.fields().forEach {
            val valueNode = it.value
            when {
                valueNode.isObject -> {
                    val filterParser = valueNode.traverse(parser.codec)
                    filters[it.key] = when {
                        valueNode.has("from") ->
                            filterParser.readValueAs(FilterDateRange::class.java)
                        else -> logger.warn("[${it.key}]는 정의되지 않은 유형입니다. 추가 구현이 필요 합니다.")
                    }
                }
                it.value.isArray -> {
                    filters[it.key] = it.value.map { element -> element.textValue() }
                }
                it.value.isTextual ->
                    filters[it.key] = it.value.textValue()
            }
        }
        return filters
    }
}
