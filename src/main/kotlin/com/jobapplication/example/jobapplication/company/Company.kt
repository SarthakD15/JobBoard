package com.jobapplication.example.jobapplication.company

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.jobapplication.example.jobapplication.job.Job
import com.jobapplication.example.jobapplication.job.JobDto
import com.jobapplication.example.jobapplication.review.Review
import com.jobapplication.example.jobapplication.review.ReviewDto
import com.jobapplication.example.jobapplication.security.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "company")
data class Company (

    @Id
    var id : String?,
    var title : String?,
    var description: String?
){
    @OneToMany(mappedBy = "company")
    @JsonIgnoreProperties("company")
    var jobs: List<Job>? = null

    @OneToMany(mappedBy = "company")
    @JsonIgnoreProperties("company")
    var reviews: MutableList<Review>?=null
//    constructor():this("","","",null){}
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    var userEntity:UserEntity?=null

    fun toDTO(): CompanyDto {
        return CompanyDto(
                id = id,
                title = title,
                description = description,
                jobs = jobs?.map { JobDto(id = it.id, title = it.title, description =it.description, minSalary = it.minSalary, maxSalary = it.maxSalary, company = CompanyDto(id = it.company?.id, title = it.company?.title), location = it.location) },
                reviews = reviews?.map { ReviewDto(id = it.id, title = it.title, description=it.description, rating = it.rating) }
        )
    }

    fun toDTOForApplication(): CompanyDto {
        return CompanyDto(
                id = id,
                title = title
        )
    }

}
