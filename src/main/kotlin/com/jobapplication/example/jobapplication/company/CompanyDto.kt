package com.jobapplication.example.jobapplication.company

import com.fasterxml.jackson.annotation.JsonInclude
import com.jobapplication.example.jobapplication.job.JobDto
import com.jobapplication.example.jobapplication.review.ReviewDto
import lombok.Data

@Data
data class CompanyDto(
        var id: String?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var title: String?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var description: String?=null,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var jobs: List<JobDto>?=null,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var reviews: List<ReviewDto>?=null

)