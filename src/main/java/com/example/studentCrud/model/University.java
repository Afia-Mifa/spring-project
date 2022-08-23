package com.example.studentCrud.model;

import javax.persistence.*;

@Entity
@Table(name = "university_tbl")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uni_id")
    private long id;

    @Column(name = "uni_name")
    private String name;
}
