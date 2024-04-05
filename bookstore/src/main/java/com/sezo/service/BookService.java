package com.sezo.service;

import com.sezo.Book;
import com.sezo.utility.IsbnGenerator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;



@ApplicationScoped
@Transactional(Transactional.TxType.SUPPORTS)
public class BookService {

    @Inject
    EntityManager entityManager;

    @Inject
    IsbnGenerator isbnGenerator;

    public Book findBook(Long id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b ORDER BY b.title DESC", Book.class);

        return query.getResultList();

    }

    public Long countAllBook() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Book createBook(Book book) {
        book.setIsbn(isbnGenerator.generateNumber());
        entityManager.merge(book);
        return book;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteBook(Long id) {
        entityManager.remove(entityManager.find(Book.class,id));
    }
}
