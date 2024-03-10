package com.jobapplication.example.jobapplication.company

import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository: JpaRepository<Company, String> {
}