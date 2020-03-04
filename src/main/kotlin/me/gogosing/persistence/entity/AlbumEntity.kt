package me.gogosing.persistence.entity

import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.UniqueConstraint

/**
 * 앨범 정보 Entity.
 * Created by JinBum Jeong on 2020/03/04.
 */
@Entity
@Table(
    name = "FLO_ALBUM",
    uniqueConstraints = [UniqueConstraint(columnNames = ["ALBUM_ID"])]
)
class AlbumEntity (

    /**
     * 레코드 식별자.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALBUM_KEY")
    var key: Long? = null,

    /**
     * 레코드 대체 식별자.
     */
    @Column(name = "ALBUM_ID", nullable = false, columnDefinition = "char(14)")
    var id: String,

    /**
     * 제목.
     */
    @Column(name = "TITLE", nullable = false, length = 150)
    var title: String,

    /**
     * 삭제여부.
     */
    @Column(name = "DELETED", nullable = false)
    var deleted: Boolean = false,

    /**
     * 레코드 생성일시.
     */
    @Column(name = "CREATE_UTC", nullable = false, updatable = false)
    var createOn: ZonedDateTime,

    /**
     * 앨범 소속 곡 목록.
     */
    @OneToMany(mappedBy = "albumEntity", fetch = FetchType.LAZY)
    var songEntities: MutableList<SongEntity> = mutableListOf(),

    /**
     * 앨범 서비스 가능 지역 코드 목록.
     */
    @OneToMany(mappedBy = "albumEntity", fetch = FetchType.LAZY)
    var albumLocaleEntities: MutableList<AlbumLocaleEntity> = mutableListOf()
)
