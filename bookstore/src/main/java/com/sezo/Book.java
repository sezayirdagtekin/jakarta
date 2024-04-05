package com.sezo;

import jakarta.json.bind.annotation.JsonbNumberFormat;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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
    @NotNull
    private String title;

    @Column(length = 10000)
    @Size(min = 10, max = 10000)
    private String description;

    @Min(1)
    @JsonbNumberFormat(locale = "en_US",value = "$#0.00")
    private BigDecimal price;

    @Column(name = "publication_date")
    @Past
    @JsonbProperty("publication-date")
    private LocalDate publicationDate;

    @Min(10)
    @Column(name = "nb_of_pages")
    @JsonbProperty("nb-of-pages")
    private Integer nbOfPages;


    @Column(name = "image_url")
    @JsonbTransient
    private String imageURL;

}