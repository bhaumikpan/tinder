package com.example.feature_domain.model

data class ResponseDataModel(
    val kind: String,
    val data: SuperData
)

data class SuperData(
    val after: String,
    val dist: Int,
    val children: List<ChildrenResponseModel>
)