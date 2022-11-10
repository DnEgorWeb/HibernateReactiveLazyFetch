package org.acme

import io.smallrye.mutiny.Uni
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/api")
internal class BookAndPagesResource {
    @Inject
    @field:Default
    lateinit var bookRepo: BookRepo

    @Inject
    @field:Default
    lateinit var pageRepo: PageRepo

    @GET
    @Path("/book/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getBook(@PathParam("id") bookId: Long): Uni<Response> {
        return bookRepo.getBook(bookId)
            .map {
                Response.status(Response.Status.OK).entity(it).build()
            }
    }

    @POST
    @Path("/book")
    @Produces(MediaType.APPLICATION_JSON)
    fun createBooks(book: BookRequest): Uni<Response> {
        return bookRepo.persistBook(book.toEntity())
            .map {
                Response.status(Response.Status.CREATED).entity(it).build()
            }
    }

    @POST
    @Path("/page")
    @Produces(MediaType.APPLICATION_JSON)
    fun createPage(req: PageRequest): Uni<Response> {
        return pageRepo
            .persistPage(req.toEntity(), req.bookId as Long)
            .map {
                Response.status(Response.Status.CREATED).entity(it).build()
            }
    }
}