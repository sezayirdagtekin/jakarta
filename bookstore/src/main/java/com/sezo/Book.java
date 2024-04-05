package com.sezo;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "T_BOOK")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String isbn;
    @Column(length = 200)
    private String title;
    @Column(length = 10000)
    private String description;
    private BigDecimal price;
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    @Column(name = "nb_of_pages")
    private Integer nbOfPages;
    @Column(name = "image_url")
    private String imageURL;

}