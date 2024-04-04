package com.sezo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {


    @GET
    public Response getAllBooks() {
        List<Book> books = new ArrayList<>();

        if (books.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(books).build();
    }


    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") Long id) {
        Book book = null;
        if (book == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(book).build();
    }


    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response count() {
        long noBooks = 0;
        if (noBooks == 0)
            return Response.noContent().build();

        return Response.ok(noBooks).build();
    }


    @POST
    public Response createBook(Book book) throws URISyntaxException {
        book.setId(1L);
        URI createdURI = new URI(book.getId().toString());

        return Response.created(createdURI).entity(book).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteBookById(@PathParam("id") Long id) {

        //Delete  book from the database
        return Response.noContent().build();
    }

}

