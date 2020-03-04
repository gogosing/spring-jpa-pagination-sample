package me.gogosing.model.paging

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 페이징 처리된 게시물 응답.
 * Created by JinBum Jeong on 2020/03/04.
 */
data class PaginationResult<T> (

    /**
     * 페이징 정보.
     */
    @JsonProperty(value = "paging")
    val paging: Paging,

    /**
     * 조회 조건.
     */
    @JsonProperty(value = "filters")
    val filters: Map<String, Any?> = emptyMap(),

    /**
     * 게시물 목록.
     */
    @JsonProperty(value = "list")
    val list: List<T> = emptyList()
)
