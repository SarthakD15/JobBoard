package com.jobapplication.example.jobapplication.company

import com.jobapplication.example.jobapplication.applications.Application
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CompanyRepository: JpaRepository<Company, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM job_board.company where id = ?1")
    fun findByCompany(id: String) : List<Company>
}