package br.com.bernardoroll.catho.domain.use_case.api_keys

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
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetApiKeysUseCaseTest : BaseTest() {

    @MockK
    private lateinit var cathoRepository: CathoRepository

    private val useCase by lazy {
        GetApiKeysUseCase(cathoRepository)
    }

    @Test
    fun `run - when get api keys successfully, then return Success`() = runBlocking {
        coEvery { cathoRepository.getApiKeys() } returns Success(mockk())

        val either = useCase.run()

        coVerify { cathoRepository.getApiKeys() }

        assertThat(either.success).isNotNull()
        assertThat(either is Success).isTrue()
    }

    @Test
    fun `run - when get api keys fails, then return Error`() = runBlocking {
        coEvery { cathoRepository.getApiKeys() } returns Error(mockk())

        val either = useCase.run()

        coVerify { cathoRepository.getApiKeys() }

        assertThat(either.error).isNotNull()
        assertThat(either is Error).isTrue()
    }
}
