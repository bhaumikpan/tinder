package com.example.feature_domain.repository

import com.example.feature_domain.model.ChildrenResponseModel
import com.example.feature_domain.model.ResponseDataModel
import com.example.network_data.extensions.CoreResult

interface FeatureRepo {
    suspend fun getOriginalJson(): CoreResult<ResponseDataModel>
    suspend fun getChildrenPostList() : CoreResult<List<ChildrenResponseModel>>
}