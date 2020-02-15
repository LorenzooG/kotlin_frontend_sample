package utils

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine

fun newContinuation(ctx: CoroutineContext) = object: Continuation<Unit>{
    override val context: CoroutineContext
        get() = ctx

    override fun resumeWith(result: Result<Unit>) { }
}

@Suppress("NOTHING_TO_INLINE")
inline fun async(
    ctx: CoroutineContext = EmptyCoroutineContext,
    noinline block: suspend () -> Unit
) {
    return block.startCoroutine(newContinuation(ctx))
}