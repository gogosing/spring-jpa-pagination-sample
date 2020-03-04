package me.gogosing.model.paging

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 페이징 모델.
 * Created by JinBum Jeong on 2020/03/04.
 */
data class Paging(

    /**
     * 페이지 번호.
     */
    @JsonProperty("no")
    val no: Int,

    /**
     * 페이지 당 노출 데이터 수.
     */
    @JsonProperty("limit")
    val limit: Int,

    /**
     * 전체 항목 수.
     */
    @JsonProperty("total")
    var total: Int = 0
)
