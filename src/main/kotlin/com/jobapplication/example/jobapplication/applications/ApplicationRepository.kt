package com.jobapplication.example.jobapplication.applications

import com.jobapplication.example.jobapplication.security.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ApplicationRepository : JpaRepository<Application,String> {


    @Query(nativeQuery = true, value = "SELECT * FROM job_board.application where job_id=?1 AND user_id = ?2")
    fun findByApplication(jid: String, uid: Int) : Application

    @Query(nativeQuery = true, value = "SELECT * FROM job_board.application where user_id = ?1")
    fun findByUser(uid: Int) : List<Application>

    @Query(nativeQuery = true, value = "SELECT * FROM job_board.application where job_id = ?1")
    fun findByJob(jid: String) : List<Application>




}