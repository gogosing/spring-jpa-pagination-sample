package me.gogosing.persistence.repository

import me.gogosing.persistence.entity.SongEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

/**
 * Created by JinBum Jeong on 2020/03/04.
 */
@Repository
interface SongRepository : JpaRepository<SongEntity, Long>, JpaSpecificationExecutor<SongEntity>