package br.com.bernardoroll.catho.service

import br.com.bernardoroll.catho.domain.model.*
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either
import br.com.bernardoroll.catho.domain.use_case.Either.Error
import br.com.bernardoroll.catho.domain.use_case.Either.Success
import br.com.bernardoroll.catho.networking.service.CathoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CathoRepositoryImpl(
    private val cathoService: CathoService
) : CathoRepository {

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
                    Error(
                        Throwable(
                            response.message()
                        )
                    )
                }
            } catch (error: Throwable) {
                Error(
                    Throwable(
                        error.message
                    )
                )
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
            } catch (error: Throwable) {
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

    @Throws(Throwable::class)
    override suspend fun getTips(
        apiKey: String
    ): Either<Throwable, List<TipModel>?> =
        withContext(Dispatchers.IO) {
            try {
                val response = cathoService.getTips(apiKey)
                if (response.isSuccessful) {
                    val list = response.body()?.map {
                        TipModel(
                            id = it.id,
                            description = it.description,
                            button = ButtonModel(
                                show = it.button?.show,
                                label = it.button?.label,
                                url = it.button?.url
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

    @Throws(Throwable::class)
    override suspend fun postTipAction(
        apiKey: String,
        token: String,
        tipId: String,
        action: String
    ): Either<Throwable, TipActionModel> =
        withContext(Dispatchers.IO) {
            try {
                val response = cathoService.postTipAction(
                    apiKey,
                    token,
                    tipId,
                    action
                )
                if (response.isSuccessful) {
                    Success(
                        TipActionModel(
                            id = response.body()?.id,
                            createdAt = response.body()?.createdAt,
                            tipId = response.body()?.tipId,
                            action = response.body()?.action,
                            message = response.body()?.message
                        )
                    )
                } else {
                    throw Throwable(response.message())
                }
            } catch (error: Throwable) {
                throw error
            }
        }
}
