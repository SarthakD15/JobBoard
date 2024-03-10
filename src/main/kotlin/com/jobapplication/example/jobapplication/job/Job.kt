package com.jobapplication.example.jobapplication.job

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.jobapplication.example.jobapplication.applications.Application
import com.jobapplication.example.jobapplication.applications.ApplicationDto
import com.jobapplication.example.jobapplication.company.Company
import com.jobapplication.example.jobapplication.company.CompanyDto
import jakarta.persistence.*

@Entity
@Table(name= "job")
data class Job (

    @Id
    var id: String?,
    var title: String?,
    var description: String?,
    var minSalary: String?,
    var maxSalary: String?,
    var location: String?,
){
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    var company: Company?=null

    @OneToMany(mappedBy = "job")
    @JsonIgnoreProperties("job")
    var application: MutableList<Application>?=null
//    constructor():this("","","","","","",null){}

    fun toDTO(): JobDto {
        return JobDto(
                id = id,
                title = title,
                description = description,
                minSalary =minSalary,
                maxSalary =maxSalary,
                location =location,
                company = company?.let { CompanyDto(id = it.id, title = it.title) },
                application = application?.map { ApplicationDto(id = it.id, name = it.name, edu = it.edu, coverLetter = it.coverLetter, status = it.status) }
        )
    }
}