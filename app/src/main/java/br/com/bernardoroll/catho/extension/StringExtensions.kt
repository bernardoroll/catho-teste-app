package br.com.bernardoroll.catho.extension

fun String.getFirstWord() =
    this.substringBefore(" ")

fun String.getFileNameFrom() =
    this.substringAfterLast("/")
