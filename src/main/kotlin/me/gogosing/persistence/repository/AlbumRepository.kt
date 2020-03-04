package me.gogosing.persistence.repository

import me.gogosing.persistence.entity.AlbumEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

/**
 * 앨범 Repository.
 * Created by JinBum Jeong on 2020/03/04.
 */
@Repository
interface AlbumRepository : JpaRepository<AlbumEntity, Long>, JpaSpecificationExecutor<AlbumEntity>