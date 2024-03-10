package com.jobapplication.example.jobapplication.review

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jobapplication.example.jobapplication.company.Company
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class Review (
        @Id
        var id: String?,
        var title: String?,
        var description: String?,
        var rating: Double?,
)
{
        @ManyToOne()
        @JoinColumn(name = "company_id")
        var company: Company?=null
}