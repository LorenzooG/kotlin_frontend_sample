package services

import USERS_API
import kotlinx.coroutines.await
import entities.User
import kotlin.browser.window
import kotlin.js.Promise

object UserService : Service<User> {

    override fun fetch(): Promise<List<User>> {
        return Promise { resolve, reject ->
            window.fetch(USERS_API).then {
                it.json().then { res ->
                    resolve(res.unsafeCast<Array<User>>().toList())
                }
            }.catch {
                reject(it)
            }
        }
    }

    override fun fetch(id: Int): Promise<User> {
        return Promise { resolve, reject ->
            window.fetch("$USERS_API/$id").then { it.json() }.then { res ->
                resolve(res.unsafeCast<User>())
            }.catch { error ->
                reject.invoke(error)
            }
        }
    }

    override fun delete(id: Int): Promise<User> {
        TODO("Not yet implemented")
    }

    override fun update(id: Int, data: User): Promise<User> {
        TODO("Not yet implemented")
    }

    override fun store(data: User): Promise<User> {
        TODO("Not yet implemented")
    }

}