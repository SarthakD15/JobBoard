package com.jobapplication.example.jobapplication.review

import com.fasterxml.jackson.annotation.JsonInclude
import com.jobapplication.example.jobapplication.company.CompanyDto
import lombok.Data

@Data
data class ReviewDto (
    var id: String?,
    var title: String?,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var description: String?=null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var rating: Double?=null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var company: CompanyDto? = null
)