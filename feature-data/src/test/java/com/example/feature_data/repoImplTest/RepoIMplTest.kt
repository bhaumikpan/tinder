package com.example.feature_data.repoImplTest

import android.util.Log
import com.example.feature_data.api.FeatureApi
import com.example.feature_data.repo.FeatureRepoImpl
import com.example.feature_domain.model.ChildrenResponseModel
import com.example.feature_domain.model.Data
import com.example.feature_domain.model.ResponseDataModel
import com.example.feature_domain.model.SuperData
import com.example.network_data.extensions.CoreResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepoIMplTest {
    private val dispatcher = UnconfinedTestDispatcher()
    private val api = mockk<FeatureApi>(relaxed = true)

    private var featureRepoImpl = FeatureRepoImpl(dispatcher, api)
    private var throwable = mockk<Throwable>(relaxed = true)

    init {
        // for any android logger used in impl code
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
    }

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset main dispatcher to the original Main dispatcher
    }

    @Test
    fun `test success Feature Response`() = runTest {
        val response = getDummyResponseObject()
        // given
        coEvery {
            api.getPostApi()
        } returns response

        // when
        when (val answer = featureRepoImpl.getChildrenPostList()) {
            // then
            is CoreResult.OnSuccess -> {
                val data = answer.data
                assert(data.isNotEmpty())
                val child = data[0].data
                assert(child != null)
                assert(child!!.title == "title")
            }

            else -> {
                assert(false)
            }
        }
    }

    @Test
    fun `test failure Feature Response`() {
        // Given
        coEvery {
            api.getPostApi()
        } throws throwable

        runTest {
            // When
            when (featureRepoImpl.getChildrenPostList()) {
                // Then
                is CoreResult.OnError -> {
                    assert(true)
                }

                else -> {
                    assert(false)
                }
            }

        }
    }

    private fun getDummyResponseObject(): ResponseDataModel {
        return ResponseDataModel(
            kind = "super kind",
            data = SuperData(
                after = "",
                dist = 1,
                children = listOf(
                    ChildrenResponseModel(
                        kind = "string_kind",
                        data = Data(
                            title = "test"
                        )
                    )
                )
            )
        )
    }

}