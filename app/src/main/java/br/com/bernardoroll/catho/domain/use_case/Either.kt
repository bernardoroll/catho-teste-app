package br.com.bernardoroll.catho.domain.use_case

sealed class Either<out E, out S> {

    data class Error<out E>(val e: E) : Either<E, Nothing>()
    data class Success<out S>(val s: S) : Either<Nothing, S>()

    val isError get() = this is Error<E>
    val isSuccess get() = this is Success<S>

    val error: E? get() = (this as Error<E>)?.e
    val success: S? get() = (this as Success<S>)?.s

    fun either(fnE: (E) -> Any, fnS: (S) -> Any): Any =
        when (this) {
            is Error -> fnE(e)
            is Success -> fnS(s)
        }
}
