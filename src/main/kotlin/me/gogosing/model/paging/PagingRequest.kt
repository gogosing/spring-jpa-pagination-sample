package me.gogosing.model.paging

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import me.gogosing.jackson.PagingFilterDeserializer

/**
 * 페이징 요청 모델.
 * Created by JinBum Jeong on 2020/03/04.
 */
data class PagingRequest(

    /**
     * 페이징 요청 모델.
     */
    @JsonProperty(value = "paging")
    val paging: Paging,

    /**
     * 필터링 조건.
     */
    @JsonDeserialize(using = PagingFilterDeserializer::class)
    @JsonProperty(value = "filters")
    val filters: Map<String, Any> = emptyMap()
)
