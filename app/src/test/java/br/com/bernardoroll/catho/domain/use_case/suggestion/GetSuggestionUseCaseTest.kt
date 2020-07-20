package br.com.bernardoroll.catho.domain.use_case.suggestion

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
class GetSuggestionUseCaseTest : BaseTest() {

    @MockK
    private lateinit var cathoRepository: CathoRepository

    private val useCase by lazy {
        GetSuggestionUseCase(cathoRepository)
    }

    @Test
    fun `run - when get suggestions successfully, then return Success`() = runBlocking {
        coEvery { cathoRepository.getSuggestions(any(), any()) } returns Success(mockk())

        val either = useCase.run(
            apiKey = API_KEY,
            token = TOKEN
        )
        coVerify { cathoRepository.getSuggestions(any(), any()) }

        assertThat(either.success).isNotNull()
        assertThat(either is Success).isTrue()
    }

    @Test
    fun `run - when get suggestions fails, then return Error`() = runBlocking {
        coEvery { cathoRepository.getSuggestions(any(), any()) } returns Error(mockk())

        val either = useCase.run(
            apiKey = API_KEY,
            token = TOKEN
        )
        coVerify { cathoRepository.getSuggestions(any(), any()) }

        assertThat(either.error).isNotNull()
        assertThat(either is Error).isTrue()
    }

    companion object {
        private const val API_KEY = "API_KEY"
        private const val TOKEN = "TOKEN"
    }
}
