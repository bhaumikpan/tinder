package com.example.feature_data.api

import com.example.feature_domain.model.ChildrenResponseModel
import com.example.feature_domain.model.ResponseDataModel

import retrofit2.http.GET

interface FeatureApi {
    @GET("/r/aww/hot.json")
    suspend fun getPostApi(): ResponseDataModel
}