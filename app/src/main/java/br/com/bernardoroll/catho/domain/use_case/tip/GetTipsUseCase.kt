package br.com.bernardoroll.catho.domain.use_case.tip

import br.com.bernardoroll.catho.domain.model.TipModel
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either

class GetTipsUseCase(
    private val repository: CathoRepository
) {

    suspend fun run(apiKey: String): Either<Throwable, List<TipModel>?> =
        try {
            repository.getTips(apiKey)
        } catch (error: Throwable) {
            throw error
        }
}
