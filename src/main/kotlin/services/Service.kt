package services

import kotlin.js.Promise

interface Service<T> {

    fun fetch(): Promise<List<T>>
    fun fetch(id: Int): Promise<T>
    fun delete(id: Int): Promise<T>
    fun store(data: T): Promise<T>
    fun update(id: Int, data: T): Promise<T>

}