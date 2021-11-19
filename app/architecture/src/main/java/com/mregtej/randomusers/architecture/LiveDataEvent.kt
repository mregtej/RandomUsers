package com.mregtej.randomusers.architecture

class LiveDataEvent<T>(private val content: T) {
    private var exposed = false

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LiveDataEvent<*>

        if(content != other.content) return false
        return true
    }

    override fun hashCode() = content?.hashCode() ?: 0

    override fun toString() =
        "LivedataEvent(content=$content, exposed=$exposed)"

    /**
     * Exposes the content once. If the content
     * was already exposed, null is returned
     */
    fun getContentOnce() = if (!exposed) {
        exposed = true
        content
    } else null

    /**
     * Exposes the content, even if it is
     * already exposed
     */
    fun peekContent() = content
}