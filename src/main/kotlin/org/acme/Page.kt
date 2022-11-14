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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Page

        if (pageId != other.pageId) return false

        return true
    }

    override fun hashCode(): Int {
        return pageId?.hashCode() ?: 0
    }
}