package me.gogosing.persistence.specification

import me.gogosing.consts.ApplicationConstants.Companion.ALBUM_SERVICE_AVAILABLE_ALL_LOCALE_CODE
import me.gogosing.persistence.entity.AlbumEntity
import me.gogosing.persistence.entity.AlbumEntity_
import me.gogosing.persistence.entity.AlbumLocaleEntity
import me.gogosing.persistence.entity.AlbumLocaleEntity_
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.Root

/**
 * Created by JinBum Jeong on 2020/03/04.
 */
interface AlbumSpecification {

    companion object {

        /**
         * 삭제처리 되지않은 유효한 레코드 조회.
         */
        fun active() = Specification<AlbumEntity> { root, _, builder ->
            builder.isFalse(root.get(AlbumEntity_.deleted))
        }

        /**
         * 곡이 수록된 앨범의 서비스 가능 지역 조회.
         */
        fun containsLocale(locale: Locale) = Specification<AlbumEntity> { root, query, _ ->
            val subQuery = query.subquery(AlbumEntity::class.java)
            val subQueryRoot: Root<AlbumLocaleEntity> = subQuery.from(AlbumLocaleEntity::class.java)

            subQuery.select(
                subQueryRoot.get(AlbumLocaleEntity_.albumEntity)
            )
            subQuery.where(
                subQueryRoot.get(AlbumLocaleEntity_.localeCode)
                    .`in`(ALBUM_SERVICE_AVAILABLE_ALL_LOCALE_CODE, locale.language)
            )

            root.`in`(subQuery)
        }
    }
}
