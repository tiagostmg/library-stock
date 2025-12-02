package com.library_stock.library_stock.location;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "location")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String sector;

    @Column(length = 50, nullable = false)
    private String aisle;

    @Column(length = 50, nullable = false)
    private String shelf;

    @Column(length = 50, nullable = false)
    private String shelfLevel;

    @Column(length = 50, nullable = false)
    private String position;

}
