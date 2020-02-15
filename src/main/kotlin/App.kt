import react.*
import react.dom.*
import react.router.dom.*
import views.UsersView
import kotlin.browser.document

fun main() {
    render(document.getElementById("root")) {
        child(App)
    }
}

val App = functionalComponent<RProps> {
    hashRouter {
        switch {
            route("/", exact = false) {
                child(UsersView)
            }
        }
    }
}