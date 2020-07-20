package br.com.bernardoroll.catho.domain.use_case.auth

import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import br.com.bernardoroll.catho.BaseTest
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.Either.Error
import br.com.bernardoroll.catho.domain.use_case.Either.Success
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAuthUseCaseTest : BaseTest() {

    @MockK
    private lateinit var cathoRepository: CathoRepository

    private val useCase by lazy {
        GetAuthUseCase(cathoRepository)
    }

    @Test
    fun `run - when get auth successfully, then return Success`() = runBlocking {
        coEvery { cathoRepository.getAuth(any(), any()) } returns Success(mockk())

        val either = useCase.run(
            apiKey = API_KEY,
            userId = USER_ID
        )

        coVerify { cathoRepository.getAuth(any(), any()) }

        assertThat(either.success).isNotNull()
        assertThat(either is Success).isTrue()
    }

    @Test
    fun `run - when get auth fails, then return Error`() = runBlocking {
        coEvery { cathoRepository.getAuth(any(), any()) } returns Error(mockk())

        val either = useCase.run(
            apiKey = API_KEY,
            userId = USER_ID
        )

        coVerify { cathoRepository.getAuth(any(), any()) }

        assertThat(either.error).isNotNull()
        assertThat(either is Error).isTrue()
    }

    companion object {
        const val API_KEY = "API_KEY"
        const val USER_ID = "USER_ID"
    }
}
