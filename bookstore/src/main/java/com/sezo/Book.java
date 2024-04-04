package com.sezo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private String description;
    private BigDecimal price;
    private LocalDate publicationDate;
    private Integer nbOfPages;
    private String imageURL;


}
