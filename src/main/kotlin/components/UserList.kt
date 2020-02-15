package components

import react.*
import entities.User
import kotlinx.html.Tag
import kotlinx.html.js.onChangeFunction
import react.dom.*
import services.UserService

private val component = functionalComponent<RProps> { props ->
    val (users, setUsers) = useState(arrayOf<User>())
    val (inputSearch, setInputSearch) = useState("")
    val (loaded, setLoaded) = useState(false)
    val (error, setError) = useState<String?>(null)

    useEffect(listOf()) {
        UserService.fetch().then { fetchedUsers ->
            setUsers(fetchedUsers.toTypedArray())
            setLoaded(true)
        }.catch { error ->
            setError(error.message ?: "")
            setLoaded(true)
        }
    }

    div(classes = "UsersList_Div_ShowUsers") {
        input {
            attrs {
                placeholder = "Search an user"
                value = inputSearch
                onChangeFunction = { event ->
                    setInputSearch((event.target.asDynamic().value ?: "") as String)
                }
            }
        }
        ul {
            useMemo({
                val filtered: List<User> =
                    if (inputSearch.isNotEmpty() || inputSearch.isNotEmpty()) {
                        users.filter {

                            var search: Any = inputSearch

                            try {
                                search = search.toString().toInt()
                            } catch (_: Throwable) {
                            }

                            it.name.startsWith(search.toString(), true)
                                    || it.username.startsWith(search.toString(), true)
                                    || it.email.startsWith(search.toString(), true)
                                    || it.id == search
                        }
                    } else {
                        users.toList()
                    }

                if (!loaded) {
                    div(classes = "UserList_Loading_UsersData") {
                        h2 { +"Wait a minute, we're loading the data for you" }
                    }
                } else if (error != null) {
                    div(classes = "UserList_FetchError_Users") {
                        h2 { +"Some error occurred! $error" }
                    }
                } else if (users.isEmpty() && loaded) {
                    div(classes = "UserList_NotFound_Users") {
                        h2 { +"Not found any user with this input!" }
                    }
                } else {
                    filtered.forEach { user ->
                        userItem(user)
                    }
                }
            }, arrayOf(users, inputSearch, error, loaded))
        }
    }

}
private val memorized = memo(fun RBuilder.(props: RProps) { child(component, props) })

fun RBuilder.userList() {
    child(memorized, object : RProps {}, listOf())
}


