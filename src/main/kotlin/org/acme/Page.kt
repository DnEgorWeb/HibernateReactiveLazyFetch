package org.acme

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode

@Entity(name = "page")
@RegisterForReflection
internal data class Page(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val pageId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    var book: Book? = Book(),
)