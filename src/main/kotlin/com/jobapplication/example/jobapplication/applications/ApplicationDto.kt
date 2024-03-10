package com.jobapplication.example.jobapplication.applications

import com.fasterxml.jackson.annotation.JsonInclude
import com.jobapplication.example.jobapplication.company.CompanyDto
import com.jobapplication.example.jobapplication.job.Job
import com.jobapplication.example.jobapplication.job.JobDto
import lombok.Data

@Data
data class ApplicationDto (
        var id: String?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var name: String?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var edu: String?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var coverLetter: String?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var status: Boolean?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var job : JobDto?=null,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var company: CompanyDto?=null

)