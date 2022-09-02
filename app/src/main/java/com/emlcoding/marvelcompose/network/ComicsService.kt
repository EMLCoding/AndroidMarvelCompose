package com.emlcoding.marvelcompose.network

import com.emlcoding.marvelcompose.network.models.ApiResponse
import com.emlcoding.marvelcompose.network.models.ApiComic
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicsService {

    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("format") format: String?
    ): ApiResponse<ApiComic>

    @GET("/v1/public/comics/{comicId}")
    suspend fun findComic(
        @Path("comicId") comicId: Int
    ): ApiResponse<ApiComic>

}