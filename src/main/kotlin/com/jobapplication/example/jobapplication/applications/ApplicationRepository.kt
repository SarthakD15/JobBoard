package com.jobapplication.example.jobapplication.applications

import org.springframework.data.jpa.repository.JpaRepository

interface ApplicationRepository : JpaRepository<Application,String> {
}