package com.sorsix.skopjecarrent.repository

import com.sorsix.skopjecarrent.model.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : JpaRepository<Location, Long> {
}