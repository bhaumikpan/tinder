package com.example.feature_data.repo


import android.util.Log
import com.example.network_data.di.IoDispatcher
import com.example.feature_data.api.FeatureApi
import com.example.feature_domain.model.ChildrenResponseModel
import com.example.feature_domain.model.ResponseDataModel
import com.example.feature_domain.repository.FeatureRepo
import com.example.network_data.extensions.CoreResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

import javax.inject.Inject

class FeatureRepoImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val api: FeatureApi
) : FeatureRepo {
    override suspend fun getOriginalJson(): CoreResult<ResponseDataModel> {
        return withContext(dispatcher) {
            runCatching {
                api.getPostApi()
            }.fold(onSuccess = { CoreResult.OnSuccess(it) }, onFailure = { CoreResult.OnError(it) })
        }
    }

    override suspend fun getChildrenPostList(): CoreResult<List<ChildrenResponseModel>> {
        return withContext(dispatcher) {
            runCatching {
                api.getPostApi()
            }.fold(
                onSuccess = { responseDataModel ->
                    CoreResult.OnSuccess(responseDataModel.data.children.filter { item ->
                        Log.d("BMK", "filter: "+ item.data.thumbnail)
                        item.data.thumbnail != null && item.data.thumbnail!!.contains("jpg", false) && !item.data.thumbnail!!.contains("png", false)
                    })
                }, onFailure = { CoreResult.OnError(it) })
        }
    }
}

/*
List {
 1,2,null, 4
}*/

/* {
List {
1,2,4}
}
 */