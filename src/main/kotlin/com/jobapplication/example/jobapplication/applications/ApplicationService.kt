package com.jobapplication.example.jobapplication.applications

import com.jobapplication.example.jobapplication.company.Company
import org.springframework.http.ResponseEntity

interface ApplicationService {
    fun getAllApplications(cid: String, jid:String): ResponseEntity<List<ApplicationDto>>
    fun createApplication(cid: String, id:String, application: Application)
    fun updateApplication(cid: String, jid: String, aid: String, application: Application)
    fun deleteApplicationById(cid: String, jid: String, aid:String):String
    fun getApplicationById(cid: String, jid: String, aid:String): Application
    fun acceptApplication(cid: String, jid:String, aid:String):String
    fun rejectApplication(cid: String, jid:String, aid:String):String
//    fun getApplicationsforuser(id:String,cid: String,jid: String): List<ApplicationDto>
}
