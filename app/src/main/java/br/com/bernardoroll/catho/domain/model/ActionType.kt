package br.com.bernardoroll.catho.domain.model

enum class ActionType(private val action: String) {
    LIKE("like"),
    DISLIKE("dislike"),
    UNDEFINED("");

    fun getValue(): String = this.action
}
