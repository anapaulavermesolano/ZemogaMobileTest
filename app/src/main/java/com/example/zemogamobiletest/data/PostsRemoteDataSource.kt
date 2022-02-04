package com.example.zemogamobiletest.data

import com.example.zemogamobiletest.model.Post
import com.example.zemogamobiletest.model.PostsDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsRemoteDataSource(apiClient: ApiClient): PostsDataSource {

    private var call: Call<PostsResponse>? = null
    private val service = apiClient.build()

    override fun getPosts(callback: OperationCallback<Post>) {
        call = service?.posts()

        call?.enqueue(object : Callback<PostsResponse> {
            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                callback.onError(t.message)
            }
            override fun onResponse(
                call: Call<PostsResponse>,
                response: Response<PostsResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it.posts)
                    } else {
                        callback.onError(it.toString())
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}