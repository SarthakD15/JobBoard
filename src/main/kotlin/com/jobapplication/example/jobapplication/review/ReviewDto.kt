package com.jobapplication.example.jobapplication.review

import lombok.Data

@Data
data class ReviewDto (
    var id: String?,
    var title: String?,
    var description: String?,
    var rating: Double?,
)