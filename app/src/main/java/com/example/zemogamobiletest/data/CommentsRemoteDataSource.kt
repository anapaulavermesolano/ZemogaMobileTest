package com.example.zemogamobiletest.data

import com.example.zemogamobiletest.model.Comments
import com.example.zemogamobiletest.model.CommentsDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsRemoteDataSource(apiClient: ApiClient): CommentsDataSource {

    private var call: Call<List<Comments>>? = null
    private val service = apiClient.build()

    override fun getComments(callback: OperationCallback<Comments>) {
        call = service?.comments()

        call?.enqueue(object : Callback<List<Comments>> {
            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                callback.onError(t.message)
            }
            override fun onResponse(
                call: Call<List<Comments>>,
                response: Response<List<Comments>>
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