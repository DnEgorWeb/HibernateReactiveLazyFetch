package org.acme

import io.quarkus.hibernate.reactive.panache.Panache
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped
import io.smallrye.mutiny.Uni
import org.hibernate.reactive.mutiny.Mutiny
import java.lang.RuntimeException

@ApplicationScoped
internal class BookRepo : PanacheRepository<Book> {
    fun getBook(bookId: Long): Uni<Book> {
        return findById(bookId)
            .call { it -> Mutiny.fetch(it.pages) }
            .map {
                it ?: throw RuntimeException("Book with id $bookId not found")
            }
    }

    fun persistBook(book: Book): Uni<Book> {
        return Panache.withTransaction {
            persist(book)
        }
    }
}