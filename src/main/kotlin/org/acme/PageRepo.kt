package org.acme

import io.quarkus.hibernate.reactive.panache.Panache
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped
import io.smallrye.mutiny.Uni

@ApplicationScoped
internal class PageRepo : PanacheRepository<Page> {
    fun persistPage(page: Page, bookId: Long): Uni<Page> {
        return Panache.withTransaction {
            getSession().chain { s ->
                val book = s.getReference(Book::class.java, bookId)
                page.book = book
                persist(page)
            }
        }
    }
}