package com.jobapplication.example.jobapplication.job

import org.springframework.data.jpa.repository.JpaRepository

interface JobRepository: JpaRepository<Job, String> {
}