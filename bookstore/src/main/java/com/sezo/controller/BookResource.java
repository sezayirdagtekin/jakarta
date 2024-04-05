package com.sezo.controller;

import com.sezo.Book;
import com.sezo.service.BookService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookService service;

    @Inject
    UriInfo uriInfo;

    @GET
    public Response getAllBooks() {
        List<Book> books = service.findAll();

        if (books.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(books).build();
    }


    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") Long id) {
        Book book = service.findBook(id);
        if (book == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(book).build();
    }


    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response count() {
        long noBooks = service.countAllBook();
        if (noBooks == 0)
            return Response.noContent().build();

        return Response.ok(noBooks).build();
    }


    @POST
    public Response createBook(Book book) throws URISyntaxException {
        book.setId(1L);
        URI createdURI = uriInfo.getAbsolutePathBuilder().path(book.getId().toString()).build();
        book = service.createBook(book);
        return Response.created(createdURI).entity(book).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteBookById(@PathParam("id") Long id) {

        service.deleteBook(id);
        return Response.noContent().build();
    }

}

