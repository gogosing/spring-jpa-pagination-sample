package me.gogosing.controller

import me.gogosing.model.Album
import me.gogosing.model.paging.PaginationResult
import me.gogosing.model.paging.PagingRequest
import me.gogosing.service.AlbumService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * 앨범 Controller.
 * Created by JinBum Jeong on 2020/03/04.
 */
@RestController
@RequestMapping("/v1")
class AlbumController(
    private val albumService: AlbumService
) {

    /**
     * 페이징 처리된 앨범 목록 조회.
     * @param pagingRequest 페이징 처리된 그룹 목록 조회 요청 정보.
     * @return 페이징 처리된 그룹 목록.
     */
    @PostMapping("/albums")
    fun paginatedGroups(
        locale: Locale,
        @RequestBody pagingRequest: PagingRequest
    ): PaginationResult<Album> = albumService.getAlbumPagination(pagingRequest, locale)
}
