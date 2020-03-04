package me.gogosing.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 앨범 정보 모델.
 * Created by JinBum Jeong on 2020/03/04.
 */
data class Album (

    /**
     * 앨범 레코드 대체 식별자.
     */
    @JsonProperty(value = "id")
    val id: String,

    /**
     * 제목.
     */
    @JsonProperty(value = "title")
    val title: String,

    /**
     * 수록곡 목록.
     */
    @JsonProperty(value = "songs")
    val songs: List<Song>
)
