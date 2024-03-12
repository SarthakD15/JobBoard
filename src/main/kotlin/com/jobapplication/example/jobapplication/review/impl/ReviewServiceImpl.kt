package com.jobapplication.example.jobapplication.review.impl

import com.jobapplication.example.jobapplication.company.Company
import com.jobapplication.example.jobapplication.company.CompanyRepository
import com.jobapplication.example.jobapplication.company.CompanyService
import com.jobapplication.example.jobapplication.review.Review
import com.jobapplication.example.jobapplication.review.ReviewDto
import com.jobapplication.example.jobapplication.review.ReviewRepository
import com.jobapplication.example.jobapplication.review.ReviewService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ReviewServiceImpl(var reviewRepository: ReviewRepository, var companyService: CompanyService, var companyRepository: CompanyRepository) : ReviewService {
    override fun getAllReviews(companyId: String): List<ReviewDto> {
        return reviewRepository.findByCompanyId(companyId).map { it.toDTOForCompany() }
    }

    override fun addReview(id: String, review: Review):Boolean {
        review.id= UUID.randomUUID().toString()
        var getcompany = companyService.getCompanyById(id)
        if (getcompany!=null){
            review.company= getcompany
            reviewRepository.save(review)
            return true
        }
        else{
            return false
        }
    }

    override fun getReview(companyId: String, reviewId: String): Review {
        var reviews: List<Review> = reviewRepository.findByCompanyId(companyId)
        return reviews.stream()
                .filter { review: Review -> review.id.equals(reviewId) }
                .findFirst()
                .orElse(null)
    }

    override fun updateReview(companyId: String, reviewId: String, review: Review): Boolean {
        if(companyService.getCompanyById(companyId)!=null){
         review.company=companyService.getCompanyById(companyId)
         review.id=reviewId
         reviewRepository.save(review)
            return true
        }
        else{
            return false
        }
    }

    override fun deleteReview(companyId: String, reviewId: String) : Boolean{
        if(companyService.getCompanyById(companyId)!=null && reviewRepository.existsById(reviewId)){
            val review: Review = reviewRepository.findById(reviewId).orElse(null)
            val getcompany : Company? = review.company
            getcompany?.reviews?.remove(review)
            review.company=null
            if (getcompany != null) {
                companyService.updateCompany(getcompany, companyId)
            }
            reviewRepository.deleteById(reviewId)
            return true
        }
        else{
            return false
        }
    }
}