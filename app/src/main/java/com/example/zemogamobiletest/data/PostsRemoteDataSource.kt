package com.example.zemogamobiletest.data

import com.example.zemogamobiletest.model.Post
import com.example.zemogamobiletest.model.PostsDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsRemoteDataSource(apiClient: ApiClient): PostsDataSource {

    private var call: Call<List<Post>>? = null
    private val service = apiClient.build()

    override fun getPosts(callback: OperationCallback<Post>) {
        call = service?.posts()

        call?.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                callback.onError(t.message)
            }
            override fun onResponse(
                call: Call<List<Post>>,
                response: Response<List<Post>>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
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