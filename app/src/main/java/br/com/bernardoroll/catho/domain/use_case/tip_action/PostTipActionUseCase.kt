package br.com.bernardoroll.catho.domain.use_case.tip_action

import br.com.bernardoroll.catho.domain.model.TipActionModel
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either

class PostTipActionUseCase(
    private val repository: CathoRepository
) {

    suspend fun run(apiKey: String, token: String, tipId: String, action: String): Either<Throwable, TipActionModel> =
        try {
            repository.postTipAction(apiKey, token, tipId, action)
        } catch (error: Throwable) {
            throw error
        }
}
