package com.example.domain.usecase

import com.example.domain.CatRepository
import com.example.domain.model.CatModel
import javax.inject.Inject

class GetCatsUseCaseImp @Inject constructor(private val repository: CatRepository) :
    GetCatsUseCase {
    override suspend fun invoke(catLimit: String, skip: String): Result<List<CatModel>> =
        repository.getCatInfo(catLimit, skip)
}