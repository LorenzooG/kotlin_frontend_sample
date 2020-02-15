package components

import react.*
import react.dom.*
import entities.User
import services.UserService

private data class UserItemProps(val user: User) : RProps

private val component = functionalComponent<UserItemProps> { props ->
    val user = props.user
    val id = user.id
    val username = user.username
    val name = user.name
    val email = user.email
    div {
        h3 {
            +"${id}º User: $username"
        }
        h4 {
            +name
        }
        p {
            +email
        }
    }
}

private val memorized = memo(fun RBuilder.(props: UserItemProps) {
    child(component, props)
}.unsafeCast<RBuilder.(RProps) -> Unit>())

fun RBuilder.userItem(user: User) {
    child(memorized, UserItemProps(user), listOf())
}
