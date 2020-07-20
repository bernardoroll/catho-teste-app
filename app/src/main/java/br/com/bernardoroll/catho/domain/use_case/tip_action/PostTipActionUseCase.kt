package br.com.bernardoroll.catho.domain.use_case.tip_action

import br.com.bernardoroll.catho.domain.model.TipActionModel
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either
import br.com.bernardoroll.catho.domain.use_case.Either.Error
import br.com.bernardoroll.catho.domain.use_case.Either.Success

class PostTipActionUseCase(
    private val repository: CathoRepository
) {

    suspend fun run(
        apiKey: String,
        token: String,
        tipId: String,
        action: String
    ): Either<Throwable, TipActionModel> =
        when (val either = repository.postTipAction(
            apiKey, token, tipId, action
        )) {
            is Success -> either
            is Error -> Error(either.e)
        }
}
