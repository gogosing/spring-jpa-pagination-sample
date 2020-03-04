package me.gogosing.persistence.specification

import me.gogosing.model.filter.FilterDateRange
import org.springframework.data.jpa.domain.Specification
import java.time.ZonedDateTime

/**
 * 공통 조회 조건 Specification.
 * Created by JinBum Jeong on 2020/03/04.
 */
interface SharedSpecification {

    companion object {

        /**
         * 범용 filter Specification.
         */
        fun <T> sharedPredicate(attributeName: String, payloadValue: Any): Specification<T> {
            return when (payloadValue) {
                is FilterDateRange -> between(attributeName, payloadValue)
                is List<*> -> matchedKeywords(attributeName, payloadValue.filterIsInstance<String>())
                is String -> containsKeyword(attributeName, payloadValue)
                else -> dual()
            }
        }

        /**
         * Specification 다중 선언시 where에 사용하는 dummy용 조건절.
         */
        private fun <T> dual() = Specification<T> { _, _, criteriaBuilder ->
            criteriaBuilder.equal(criteriaBuilder.literal(1), 1)
        }

        /**
         * 특정 column에 대한 like 검색.
         */
        private fun <T> containsKeyword(
            attributeName: String,
            filterKeyword: String
        ) = Specification<T> { root, _, criteriaBuilder ->
            criteriaBuilder.like(
                criteriaBuilder.upper(root.get(attributeName)), "%${filterKeyword.toUpperCase()}%"
            )
        }

        /**
         * 특정 column에 대한 in 검색.
         */
        private fun <T> matchedKeywords(
            attributeName: String,
            keywords: List<String>
        ) = Specification<T> { root, _, criteriaBuilder ->
            criteriaBuilder.and(
                root.get<String>(attributeName).`in`(keywords)
            )
        }

        /**
         * 특정 컬럼 기간 검색(between A and B).
         */
        private fun <T> between(
            attributeName: String,
            filterDateRange: FilterDateRange
        ) = Specification<T> { root, _, criteriaBuilder ->
            val column = root.get<ZonedDateTime>(attributeName)
            criteriaBuilder.and(
                criteriaBuilder.greaterThanOrEqualTo(column, filterDateRange.from),
                criteriaBuilder.lessThanOrEqualTo(column, filterDateRange.to)
            )
        }
    }
}
