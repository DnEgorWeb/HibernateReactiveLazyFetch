package org.acme

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.*
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode

@Entity(name = "book")
@RegisterForReflection
internal data class Book (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val bookId: Long? = null,

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    val pages: MutableSet<Page> = mutableSetOf(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (bookId != other.bookId) return false

        return true
    }

    override fun hashCode(): Int {
        return bookId?.hashCode() ?: 0
    }
}