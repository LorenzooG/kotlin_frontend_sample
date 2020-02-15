package views

import components.styledComponent
import components.userList
import react.*
import react.dom.*

val UsersView = functionalComponent<RProps> {
    h1 { +"All site users" }
    styledComponent()
    userList()
}