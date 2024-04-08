package com.sezo.controller;

import com.sezo.Book;
import com.sezo.service.BookService;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jdk.jfr.ContentType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/books")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class BookResource {

    @Inject
    BookService service;

    @Inject
    UriInfo uriInfo;

    @GET
    @Operation(description = "Returns all book from database")
    @APIResponse(responseCode = "200", description = "Books found", content = @Content(mediaType=APPLICATION_JSON,schema = @Schema(implementation = Book.class,type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No books found")
    public Response getAllBooks() {
        List<Book> books = service.findAll();

        if (books.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(books).build();
    }


    @GET
    @Path("/{id}")
    @Operation(description = "Returns a book  for a given identifier")
    @APIResponse(responseCode = "200", description = "A book", content = @Content(mediaType=APPLICATION_JSON,schema = @Schema(implementation = Book.class)))
    @APIResponse(responseCode = "404", description = "No book found for this identifier")
    public Response getBookById(@PathParam("id") @Min(1) Long id) {
        Book book = service.findBook(id);
        if (book == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(book).build();
    }


    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(description = "Returns  number of books  from database")
    public Response count() {
        long noBooks = service.countAllBook();
        if (noBooks == 0)
            return Response.noContent().build();

        return Response.ok(noBooks).build();
    }


    @POST
    @Operation(description = "Create valid book")
    public Response createBook(Book book) throws URISyntaxException {
        book.setId(1L);
        URI createdURI = uriInfo.getAbsolutePathBuilder().path(book.getId().toString()).build();
        book = service.createBook(book);
        return Response.created(createdURI).entity(book).build();
    }


    @DELETE
    @Path("/{id}")
    @Operation(description = "Deletes existing books")
    public Response deleteBookById(@PathParam("id")  @Min(1) Long id) {

        service.deleteBook(id);
        return Response.noContent().build();
    }

}

