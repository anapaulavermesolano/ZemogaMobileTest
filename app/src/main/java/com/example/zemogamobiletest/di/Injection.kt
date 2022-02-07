package com.example.zemogamobiletest.di

import com.example.zemogamobiletest.data.ApiClient
import com.example.zemogamobiletest.data.CommentsRemoteDataSource
import com.example.zemogamobiletest.data.PostsRemoteDataSource
import com.example.zemogamobiletest.model.CommentsDataSource
import com.example.zemogamobiletest.model.CommentsRepository
import com.example.zemogamobiletest.model.PostsDataSource
import com.example.zemogamobiletest.model.PostsRepository
import com.example.zemogamobiletest.viewmodel.CommentsViewModelFactory
import com.example.zemogamobiletest.viewmodel.ViewModelFactory

object Injection {
    private var postDataSource: PostsDataSource? = null
    private var commentsDataSource: CommentsDataSource? = null
    private var postsRepository: PostsRepository? = null
    private var commentsRepository: CommentsRepository? = null
    private var postViewModelFactory: ViewModelFactory? = null
    private var commentsViewModelFactory: CommentsViewModelFactory? = null

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

    private fun createCommentsDataSource(): CommentsDataSource {
        val dataSource = CommentsRemoteDataSource(ApiClient)
        commentsDataSource = dataSource
        return dataSource
    }

    private fun createCommentsRepository(): CommentsRepository {
        val repository = CommentsRepository(provideCommentsDataSource())
        commentsRepository = repository
        return repository
    }

    private fun createFactory(): ViewModelFactory {
        val factory = ViewModelFactory(providerRepository())
        postViewModelFactory = factory
        return factory
    }

    private fun createCommentsFactory(): CommentsViewModelFactory {
        val factory = CommentsViewModelFactory(providerCommentsRepository())
        commentsViewModelFactory = factory
        return factory
    }

    private fun provideDataSource() = postDataSource ?: createPostsDataSource()
    private fun providerRepository() = postsRepository ?: createPostsRepository()

    private fun provideCommentsDataSource() = commentsDataSource ?: createCommentsDataSource()
    private fun providerCommentsRepository() = commentsRepository ?: createCommentsRepository()

    fun provideViewModelFactory() = postViewModelFactory ?: createFactory()
    fun provideCommentsViewModelFactory() = commentsViewModelFactory ?: createCommentsFactory()

    fun destroy() {
        postDataSource = null
        postsRepository = null
        postViewModelFactory = null
        commentsDataSource = null
        commentsRepository = null
        commentsViewModelFactory = null
    }
}