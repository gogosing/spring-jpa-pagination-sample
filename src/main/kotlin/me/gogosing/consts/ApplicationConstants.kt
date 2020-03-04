package me.gogosing.consts

/**
 * 전역 상수 모음 클래스.
 * Created by JinBum Jeong on 2020/03/04.
 */
interface ApplicationConstants {

    companion object {

        /**
         * 페이지당 게시물 기본 단위.
         */
        const val DEFAULT_PAGE_LIMIT = 10

        /**
         * 앨범 서비스 가능 지역이 '전체'인 경우의 localeCode.
         */
        const val ALBUM_SERVICE_AVAILABLE_ALL_LOCALE_CODE = "all"
    }
}
