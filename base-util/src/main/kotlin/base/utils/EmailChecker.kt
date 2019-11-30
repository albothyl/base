@file:JvmName("EmailChecker")

package base.utils

private val eMailRegex = Regex(pattern = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})\$")

fun check(email: String): Boolean {
    return eMailRegex.containsMatchIn(input = email)
}
