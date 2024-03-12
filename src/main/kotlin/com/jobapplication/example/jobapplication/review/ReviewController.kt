package com.jobapplication.example.jobapplication.review

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
//@RequestMapping("/companies/{companyId}")
class ReviewController(var reviewService: ReviewService){

    @GetMapping("/allreviews/{companyId}")
    fun getAllReviews(@PathVariable companyId: String): List<ReviewDto> = reviewService.getAllReviews(companyId)

    @PostMapping("/reviews/{companyId}")
    @PreAuthorize("hasAuthority('USER')")
    fun addReview(@PathVariable companyId: String, @RequestBody review : Review)= reviewService.addReview(companyId,review)

    @GetMapping("/reviews/{companyId}/{reviewId}")
    @PreAuthorize("hasAuthority('USER')")
    fun getReview(@PathVariable companyId: String, @PathVariable reviewId: String)=reviewService.getReview(companyId,reviewId)

    @PutMapping("/reviews/{companyId}/{reviewId}")
    @PreAuthorize("hasAuthority('USER')")
    fun updateReview(@PathVariable companyId: String, @PathVariable reviewId: String, @RequestBody review: Review)=reviewService.updateReview(companyId, reviewId, review)

    @DeleteMapping("/reviews/{companyId}/{reviewId}")
    @PreAuthorize("hasAuthority('USER')")
    fun deleteReview(@PathVariable companyId: String, @PathVariable reviewId: String)=reviewService.deleteReview(companyId, reviewId)
}