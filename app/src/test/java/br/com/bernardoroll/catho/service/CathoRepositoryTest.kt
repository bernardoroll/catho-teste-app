package br.com.bernardoroll.catho.service

import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import br.com.bernardoroll.catho.BaseTest
import br.com.bernardoroll.catho.domain.model.*
import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.networking.service.CathoService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class CathoRepositoryTest : BaseTest() {

    @MockK
    private lateinit var cathoService: CathoService

    private val repository: CathoRepository by lazy {
        CathoRepositoryImpl(cathoService)
    }

    @Test
    fun `getApiKeys - when get API keys successfully, then return ApiKeyModel`() = runBlocking {
        coEvery {
            cathoService.getApiKeys()
        } returns Response.success(mockk(relaxed = true))

        val either = repository.getApiKeys()

        coVerify { cathoService.getApiKeys() }

        assertThat(either.success).isNotNull()
        assertThat(either.success is ApiKeysModel).isTrue()
    }

    @Test
    fun `getApiKeys - when get API keys fails, then return Error`() = runBlocking {
        coEvery {
            cathoService.getApiKeys()
        } throws Throwable()

        val either = repository.getApiKeys()

        coVerify { cathoService.getApiKeys() }

        assertThat(either.error).isNotNull()
        assertThat(either.error is Throwable).isTrue()
    }

    @Test
    fun `getAuth - when get auth successfully, then return AuthModel`() = runBlocking {
        coEvery {
            cathoService.getAuth(any(), any())
        } returns Response.success(mockk(relaxed = true))

        val either = repository.getAuth(
            apiKey = API_KEY,
            userId = USER_ID
        )

        coVerify { cathoService.getAuth(any(), any()) }

        assertThat(either.success).isNotNull()
        assertThat(either.success is AuthModel).isTrue()
    }

    @Test
    fun `getAuth - when get auth fails, then return Error`() = runBlocking {
        coEvery {
            cathoService.getAuth(any(), any())
        } throws Throwable()

        val either = repository.getAuth(
            apiKey = API_KEY,
            userId = USER_ID
        )

        coVerify { cathoService.getAuth(any(), any()) }

        assertThat(either.error).isNotNull()
        assertThat(either.error is Throwable).isTrue()
    }

    @Test
    fun `getSuggestions - when get suggestions successfully, then return List of SuggestionModel`() = runBlocking {
        coEvery {
            cathoService.getSuggestions(any(), any())
        } returns Response.success(mockk(relaxed = true))

        val either = repository.getSuggestions(
            apiKey = API_KEY,
            token = TOKEN
        )

        coVerify { cathoService.getSuggestions(any(), any()) }

        assertThat(either.success).isNotNull()
        assertThat(either.success is List<SuggestionModel>).isTrue()
    }

    @Test
    fun `getSuggestions - when get suggestions fails, then return Error`() = runBlocking {
        coEvery {
            cathoService.getSuggestions(any(), any())
        } throws Throwable()

        val either = repository.getSuggestions(
            apiKey = API_KEY,
            token = TOKEN
        )

        coVerify { cathoService.getSuggestions(any(), any()) }

        assertThat(either.error).isNotNull()
        assertThat(either.error is Throwable).isTrue()
    }

    @Test
    fun `getTips - when get tips successfully, then return List of TipModel`() = runBlocking {
        coEvery {
            cathoService.getTips(any())
        } returns Response.success(mockk(relaxed = true))

        val either = repository.getTips(
            apiKey = API_KEY
        )

        coVerify { cathoService.getTips(any()) }

        assertThat(either.success).isNotNull()
        assertThat(either.success is List<TipModel>).isTrue()
    }

    @Test
    fun `getTips - when get tips fails, then return Error`() = runBlocking {
        coEvery {
            cathoService.getTips(any())
        } throws Throwable()

        val either = repository.getTips(
            apiKey = API_KEY
        )

        coVerify { cathoService.getTips(any()) }

        assertThat(either.error).isNotNull()
        assertThat(either.error is Throwable).isTrue()
    }

    @Test
    fun `postTipAction - when post tip actiom successfully, then return TipActionModel`() = runBlocking {
        coEvery {
            cathoService.postTipAction(any(), any(), any(), any())
        } returns Response.success(mockk(relaxed = true))

        val either = repository.postTipAction(
            apiKey = API_KEY,
            token = TOKEN,
            tipId = TIP_ID,
            action = ACTION
        )

        coVerify { cathoService.postTipAction(any(), any(), any(), any()) }

        assertThat(either.success).isNotNull()
        assertThat(either.success is TipActionModel).isTrue()
    }

    @Test
    fun `postTipAction - when post tip action fails, then return Error`() = runBlocking {
        coEvery {
            cathoService.postTipAction(any(), any(), any(), any())
        } throws Throwable()

        val either = repository.postTipAction(
            apiKey = API_KEY,
            token = TOKEN,
            tipId = TIP_ID,
            action = ACTION
        )

        coVerify { cathoService.postTipAction(any(), any(), any(), any()) }

        assertThat(either.error).isNotNull()
        assertThat(either.error is Throwable).isTrue()
    }

    companion object {
        private const val API_KEY = "API_KEY"
        private const val USER_ID = "USER_ID"
        private const val TOKEN = "TOKEN"
        private const val TIP_ID = "TIP_ID"
        private const val ACTION = "ACTION"
    }
}
