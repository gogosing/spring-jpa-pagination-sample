package me.gogosing.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 곡 정보 모델.
 * Created by JinBum Jeong on 2020/03/04.
 */
data class Song (

    /**
     * 곡 레코드 대체 식별자.
     */
    @JsonProperty(value = "id")
    val id: String,

    /**
     * 제목.
     */
    @JsonProperty(value = "title")
    val title: String,

    /**
     * 트랙 번호.
     */
    @JsonProperty(value = "track")
    val track: Int,

    /**
     * 길이.
     */
    @JsonProperty(value = "length")
    val length: Int
)
