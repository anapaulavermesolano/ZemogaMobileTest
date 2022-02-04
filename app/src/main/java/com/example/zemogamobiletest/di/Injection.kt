package com.example.zemogamobiletest.di

import com.example.zemogamobiletest.data.ApiClient
import com.example.zemogamobiletest.data.PostsRemoteDataSource
import com.example.zemogamobiletest.model.PostsDataSource
import com.example.zemogamobiletest.model.PostsRepository
import com.example.zemogamobiletest.viewmodel.ViewModelFactory

object Injection {
    private var postDataSource: PostsDataSource? = null
    private var postsRepository: PostsRepository? = null
    private var postViewModelFactory: ViewModelFactory? = null

    private fun createPostsDataSource(): PostsDataSource {
        val dataSource = PostsRemoteDataSource(ApiClient)
        postDataSource = dataSource
        return dataSource
    }

    private fun createPostsRepository(): PostsRepository {
        val repository = PostsRepository(provideDataSource())
        postsRepository = repository
        return repository
    }

    private fun createFactory(): ViewModelFactory {
        val factory = ViewModelFactory(providerRepository())
        postViewModelFactory = factory
        return factory
    }

    private fun provideDataSource() = postDataSource ?: createPostsDataSource()
    private fun providerRepository() = postsRepository ?: createPostsRepository()

    fun provideViewModelFactory() = postViewModelFactory ?: createFactory()

    fun destroy() {
        postDataSource = null
        postsRepository = null
        postViewModelFactory = null
    }
}