package com.jobapplication.example.jobapplication.applications

import com.jobapplication.example.jobapplication.company.Company
import com.jobapplication.example.jobapplication.company.CompanyDto
import com.jobapplication.example.jobapplication.job.Job
import com.jobapplication.example.jobapplication.job.JobDto
import com.jobapplication.example.jobapplication.review.ReviewDto
import jakarta.persistence.*

@Entity
@Table(name = "application")
data class Application (
        @Id
        var id: String?,
        var name: String?,
        var edu: String?,
        var coverLetter: String?,
        var status: Boolean?
){
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    var job : Job?=null

    fun toDTO(): ApplicationDto {
        return ApplicationDto(
                id = id,
                name = name,
                edu = edu,
                coverLetter = coverLetter,
                status = status,
                job = job?.toDTOForApplication(),
                company = job?.company?.toDTOForApplication()
//                reviews = reviews?.map { ReviewDto(id = it.id, title = it.title, description=it.description, rating = it.rating) }
        )
    }
}