package me.gogosing.persistence.entity

import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn
import javax.persistence.UniqueConstraint

/**
 * 앨범 서비스 가능 지역 코드 정보 Entity.
 * Created by JinBum Jeong on 2020/03/04.
 */
@Entity
@Table(
    name = "FLO_ALB_LOCALE",
    uniqueConstraints = [UniqueConstraint(columnNames = ["ALBUM_KEY", "LOCALE_CODE"])]
)
class AlbumLocaleEntity (

    /**
     * 레코드 식별자.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCALE_KEY")
    var key: Long? = null,

    /**
     * 소속 앨범 레코드 식별자.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ALBUM_KEY", referencedColumnName = "ALBUM_KEY", nullable = false)
    var albumEntity: AlbumEntity,

    /**
     * 서비스 가능 지역 코드(ISO 3166-1 alpha-2).
     */
    @Column(name = "LOCALE_CODE", nullable = false, columnDefinition = "char(3)")
    var localeCode: String,

    /**
     * 레코드 생성일시.
     */
    @Column(name = "CREATE_UTC", nullable = false, updatable = false)
    var createOn: ZonedDateTime
)

