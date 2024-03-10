package com.jobapplication.example.jobapplication.review

import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, String> {
    fun findByCompanyId(companyId:String):List<Review>
}