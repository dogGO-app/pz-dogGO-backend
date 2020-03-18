package pl.put.poznan.pz.doggo.modules.auth.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserEntityRepository : JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String): UserEntity?
    fun existsByEmail(email: String): Boolean
}