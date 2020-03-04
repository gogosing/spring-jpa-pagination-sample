package me.gogosing.model.filter

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.ZonedDateTime

/**
 * 기간 필터링 조건 모델.
 * Created by JinBum Jeong on 2020/03/04.
 */
data class FilterDateRange(

    /**
     * 필터링 적용 시작일 (해당 일 포함).
     */
    @JsonProperty(value = "from")
    val from: ZonedDateTime,

    /**
     * 필터링 적용 종료일 (해당 일 포함).
     */
    @JsonProperty(value = "to")
    val to: ZonedDateTime
)
