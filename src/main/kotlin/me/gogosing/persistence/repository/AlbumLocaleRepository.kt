package me.gogosing.persistence.repository

import me.gogosing.persistence.entity.AlbumLocaleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

/**
 * 앨범 서비스 가능 지역 Repository.
 * Created by JinBum Jeong on 2020/03/04.
 */
@Repository
interface AlbumLocaleRepository : JpaRepository<AlbumLocaleEntity, Long>, JpaSpecificationExecutor<AlbumLocaleEntity>