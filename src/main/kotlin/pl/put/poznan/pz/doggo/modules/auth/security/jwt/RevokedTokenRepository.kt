package pl.put.poznan.pz.doggo.modules.auth.security.jwt

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RevokedTokenRepository : JpaRepository<RevokedToken, UUID>