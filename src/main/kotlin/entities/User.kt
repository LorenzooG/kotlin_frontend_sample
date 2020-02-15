package entities

data class User (
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: GeoLocation,
    val phone: String,
    val website: String,
    val company: Company
): Entity