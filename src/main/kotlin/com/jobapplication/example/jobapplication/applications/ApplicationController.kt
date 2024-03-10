package com.jobapplication.example.jobapplication.Applications

import com.jobapplication.example.jobapplication.applications.Application
import com.jobapplication.example.jobapplication.applications.ApplicationService
import jakarta.persistence.Id
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
//@RequestMapping("/{cid}")
class ApplicationController(val applicationService: ApplicationService) {

    @GetMapping("/applications/{cid}/applications/{jid}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun findAll(@PathVariable cid:String, @PathVariable jid: String)=applicationService.getAllApplications(cid, jid)

    @PostMapping("/post/{cid}/apply/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun createApplication(@PathVariable cid:String, @PathVariable id:String, @RequestBody application: Application)=applicationService.createApplication(cid, id, application)

    @PutMapping("/{cid}/apply/{jid}/{aid}")
    @PreAuthorize("hasAuthority('USER')")
    fun updateApplication(@PathVariable cid:String, @PathVariable jid:String, @PathVariable aid:String, @RequestBody application: Application)=applicationService.updateApplication(cid, jid,aid, application)

    @GetMapping("/{cid}/apply/{jid}/{aid}")
    @PreAuthorize("hasAuthority('USER')")
    fun getApplicationById(@PathVariable cid:String, @PathVariable jid: String, @PathVariable aid: String)=applicationService.getApplicationById(cid, jid,aid)

    @DeleteMapping("/{cid}/apply/{jid}/{aid}")
    @PreAuthorize("hasAuthority('USER')")
    fun deleteApplicationById(@PathVariable cid:String, @PathVariable jid: String, @PathVariable aid: String)=applicationService.deleteApplicationById(cid, jid,aid)

    @PutMapping("/applications/{cid}/applications/{jid}/{aid}/accept")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun acceptApplication(@PathVariable cid:String, @PathVariable jid: String, @PathVariable aid: String)=applicationService.acceptApplication(cid, jid,aid)

    @PutMapping("/applications/{cid}/applications/{jid}/{aid}/reject")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun rejectApplication(@PathVariable cid:String, @PathVariable jid:String, @PathVariable aid: String)=applicationService.rejectApplication(cid, jid,aid)



}