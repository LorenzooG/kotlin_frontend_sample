package utils

operator fun Any?.not(): Boolean {
    return this == null
}