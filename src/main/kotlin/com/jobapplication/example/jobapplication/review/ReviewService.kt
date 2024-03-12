package com.jobapplication.example.jobapplication.review

import com.jobapplication.example.jobapplication.job.Job

interface ReviewService {
    fun getAllReviews(companyId: String):List<ReviewDto>
    fun addReview(id: String,review: Review):Boolean
    fun getReview(companyId: String, reviewId:String): Review
    fun updateReview(companyId: String, reviewId:String,review: Review): Boolean
    fun deleteReview(companyId: String, reviewId:String):Boolean
}