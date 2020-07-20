package br.com.bernardoroll.catho.domain.use_case.tip

import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import br.com.bernardoroll.catho.BaseTest
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either.Error
import br.com.bernardoroll.catho.domain.use_case.Either.Success
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class GetTipsUseCaseTest : BaseTest() {

    @MockK
    private lateinit var cathoRepository: CathoRepository

    private val useCase by lazy {
        GetTipsUseCase(cathoRepository)
    }

    @Test
    fun `run - when get tips successfully, then return Success`() = runBlocking {
        coEvery { cathoRepository.getTips(any()) } returns Success(mockk())

        val either = useCase.run(
            apiKey = API_KEY
        )

        coVerify { cathoRepository.getTips(any()) }

        assertThat(either.success).isNotNull()
        assertThat(either is Success).isTrue()
    }

    @Test
    fun `run - when get tips fails, then return Error`() = runBlocking {
        coEvery { cathoRepository.getTips(any()) } returns Error(mockk())

        val either = useCase.run(
            apiKey = API_KEY
        )

        coVerify { cathoRepository.getTips(any()) }

        assertThat(either.error).isNotNull()
        assertThat(either is Error).isTrue()
    }

    companion object {
        private const val API_KEY = "API_KEY"
    }
}
