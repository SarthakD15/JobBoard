package com.jobapplication.example.jobapplication.job

import com.fasterxml.jackson.annotation.JsonInclude
import com.jobapplication.example.jobapplication.applications.ApplicationDto
import com.jobapplication.example.jobapplication.company.Company
import com.jobapplication.example.jobapplication.company.CompanyDto
import lombok.Data

@Data
data class JobDto(
        var id: String?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var title: String?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var description: String?=null,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var minSalary: String?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var maxSalary: String?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var location: String?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var company: CompanyDto?=null,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var application: List<ApplicationDto>? =null

)