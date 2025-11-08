package com.library_stock.library_stock.modules.location;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="location")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String sector;

    @Column(length = 50)
    private String aisle;

    @Column(length = 50)
    private String shelf;

    @Column(length = 50)
    private String shelfLevel;

    @Column(length = 50)
    private String position;

    @Column(length = 20)
    private String classificationCode;
}
