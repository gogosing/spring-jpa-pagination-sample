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
 * 곡 정보 Entity.
 * Created by JinBum Jeong on 2020/03/04.
 */
@Entity
@Table(
    name = "FLO_SONG",
    uniqueConstraints = [UniqueConstraint(columnNames = ["SONG_ID"])]
)
class SongEntity (

    /**
     * 레코드 식별자.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SONG_KEY")
    var Long: Long? = null,

    /**
     * 레코드 대체 식별자.
     */
    @Column(name = "SONG_ID", nullable = false, columnDefinition = "char(14)")
    var id: String,
    
    /**
     * 소속 앨범 레코드 식별자.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ALBUM_KEY", referencedColumnName = "ALBUM_KEY", nullable = false)
    var albumEntity: AlbumEntity,
    
    /**
     * 제목.
     */
    @Column(name = "TITLE", nullable = false, length = 150)
    var title: String,
    
    /**
     * 곡 길이 (단위: 초(second)).
     */
    @Column(name = "LENGTH", nullable = false)
    var length: Int,
    
    /**
     * 트랙 번호.
     */
    @Column(name = "TRACK_NO", nullable = false, columnDefinition = "tinyint")
    var trackNo: Int,
    
    /**
     * 삭제여부.
     */
    @Column(name = "DELETED", nullable = false)
    var deleted: Boolean = false,
    
    /**
     * 레코드 생성일시.
     */
    @Column(name = "CREATE_UTC", nullable = false, updatable = false)
    var createOn: ZonedDateTime
)
