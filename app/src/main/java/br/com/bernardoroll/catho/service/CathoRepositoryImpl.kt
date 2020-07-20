package br.com.bernardoroll.catho.service

import br.com.bernardoroll.catho.domain.model.ApiKeysModel
import br.com.bernardoroll.catho.domain.model.AuthModel
import br.com.bernardoroll.catho.domain.model.SalaryModel
import br.com.bernardoroll.catho.domain.model.SuggestionModel
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either
import br.com.bernardoroll.catho.domain.use_case.Either.Success
import br.com.bernardoroll.catho.networking.service.CathoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CathoRepositoryImpl(
    private val cathoService: CathoService
) : CathoRepository {

    @Throws(Throwable::class)
    override suspend fun getApiKeys(): Either<Throwable, ApiKeysModel> =
        withContext(Dispatchers.IO) {
            try {
                val response = cathoService.getApiKeys()
                if (response.isSuccessful) {
                    Success(
                        ApiKeysModel(
                            auth = response.body()?.auth,
                            tips = response.body()?.tips,
                            suggestion = response.body()?.suggestion,
                            survey = response.body()?.survey
                        )
                    )
                } else {
                    throw Throwable(response.message())
                }
            } catch(error: Throwable) {
                throw error
            }
        }

    @Throws(Throwable::class)
    override suspend fun getAuth(
        apiKey: String,
        userId: String
    ): Either<Throwable, AuthModel> =
        withContext(Dispatchers.IO) {
            try {
                val response = cathoService.getAuth(apiKey, userId)
                if (response.isSuccessful) {
                    Success(
                        AuthModel(
                            id = response.body()?.id,
                            name = response.body()?.name,
                            token = response.body()?.token,
                            photo = response.body()?.photo
                        )
                    )
                } else {
                    throw Throwable(response.message())
                }
            } catch(error: Throwable) {
                throw error
            }
        }

    @Throws(Throwable::class)
    override suspend fun getSuggestions(
        apiKey: String,
        token: String
    ): Either<Throwable, List<SuggestionModel>?> =
        withContext(Dispatchers.IO) {
            try {
                val response = cathoService.getSuggestions(apiKey, token)
                if (response.isSuccessful) {
                    val list = response.body()?.map {
                        SuggestionModel(
                            jobAdTile = it.jobAdTile,
                            company = it.company,
                            date = it.date,
                            locations = it.locations?.map { location -> location },
                            totalPositions = it.totalPositions,
                            salary = SalaryModel(
                                real = it.salary.real,
                                range = it.salary.range
                            )
                        )
                    }
                    Success(list)
                } else {
                    throw Throwable(response.message())
                }
            } catch (error: Throwable) {
                throw error
            }
        }
}
