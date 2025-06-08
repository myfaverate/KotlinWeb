package edu.tyut.spring_boot_ssm.context

import kotlin.coroutines.CoroutineContext

@ConsistentCopyVisibility
internal data class UserContext internal constructor(
    internal val id: Int,
    internal val username: String,
    override val key: CoroutineContext.Key<UserContext> = Key,
) : CoroutineContext.Element {
    internal companion object Key : CoroutineContext.Key<UserContext>
}