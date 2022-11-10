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
)