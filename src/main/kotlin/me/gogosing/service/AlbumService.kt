package me.gogosing.service

import me.gogosing.model.Album
import me.gogosing.model.Song
import me.gogosing.model.paging.PaginationResult
import me.gogosing.model.paging.Paging
import me.gogosing.model.paging.PagingRequest
import me.gogosing.persistence.entity.AlbumEntity
import me.gogosing.persistence.entity.AlbumEntity_
import me.gogosing.persistence.repository.AlbumRepository
import me.gogosing.persistence.specification.AlbumSpecification
import me.gogosing.persistence.specification.SharedSpecification
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*

/**
 * 앨범 서비스 Interface.
 * Created by JinBum Jeong on 2020/03/04.
 */
interface AlbumService {

    /**
     * 페이징 처리된 앨범 목록 조회.
     * @param pagingRequest 요청 페이징 정보.
     * @param locale 검색을 요청하는 사용자의 지역.
     * @return 페이징 처리된 앨범 목록.
     */
    fun getAlbumPagination(
        pagingRequest: PagingRequest,
        locale: Locale
    ): PaginationResult<Album>
}

/**
 * 페이징 검색 조건 mapping.
 */
private val ALBUM_FILTER_MAP = mapOf(
    "id" to AlbumEntity_.ID,
    "title" to AlbumEntity_.TITLE,
    "createOn" to AlbumEntity_.CREATE_ON
)

@Service
class AlbumServiceImpl(
    private val albumRepository: AlbumRepository
) : AlbumService {

    override fun getAlbumPagination(
        pagingRequest: PagingRequest,
        locale: Locale
    ): PaginationResult<Album> {
        val specification = getAlbumFilterSpecification(pagingRequest.filters, locale)
        val pageRequest = PageRequest.of(
            pagingRequest.paging.no - 1,
            pagingRequest.paging.limit,
            Sort.by(
                Sort.Order(Sort.Direction.DESC, AlbumEntity_.CREATE_ON),
                Sort.Order(Sort.Direction.ASC, AlbumEntity_.TITLE)
            )
        )

        val paginatedResult = albumRepository.findAll(specification, pageRequest)

        val totalElements = paginatedResult.totalElements.toInt()
        val paging = Paging(
            no = pagingRequest.paging.no,
            limit = pagingRequest.paging.limit,
            total = totalElements
        )

        var list = emptyList<Album>()
        if (totalElements > 0) {
            list = paginatedResult.content.map {
                Album(
                    id = it.id,
                    title = it.title,
                    songs = it.songEntities
                        .sortedBy { songEntity -> songEntity.trackNo }
                        .map { songEntity -> Song(
                                id = songEntity.id,
                                title = songEntity.title,
                                track = songEntity.trackNo,
                                length = songEntity.length
                            )
                        }
                )
            }
        }
        return PaginationResult(paging = paging, filters = pagingRequest.filters, list = list)
    }

    /**
     * 앨범 정보 조회를 위한 Specification 생성.
     * @param filters 조회 조건.
     * @param locale 검색을 요청하는 사용자의 지역.
     * @return 조회를 위한 Specification.
     */
    private fun getAlbumFilterSpecification(
        filters: Map<String, Any>,
        locale: Locale
    ): Specification<AlbumEntity>? {
        var specification = Specification
            .where(AlbumSpecification.active())
            ?.and(AlbumSpecification.containsLocale(locale))

        for (filterMap in ALBUM_FILTER_MAP) {
            val payloadValue = filters[filterMap.key]
            if (payloadValue != null) {
                val attributeName = filterMap.value
                specification = specification
                    ?.and(SharedSpecification.sharedPredicate(attributeName, payloadValue))
            }
        }
        return specification
    }
}
