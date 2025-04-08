package com.example.domain

import com.example.domain.model.CatModel

interface CatRepository {
    suspend fun getCatInfo(catsLimit: String, skip: String): Result<List<CatModel>>
}