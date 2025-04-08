package com.example.domain.usecase

import com.example.domain.model.CatModel

interface GetCatsUseCase {
    suspend operator fun invoke(catLimit: String, skip: String): Result<List<CatModel>>
}