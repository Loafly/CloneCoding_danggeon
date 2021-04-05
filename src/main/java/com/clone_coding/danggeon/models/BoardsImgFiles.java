package com.clone_coding.danggeon.models;

import com.sun.istack.Nullable;

import javax.persistence.*;

public class BoardsImgFiles {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String filePath;


    @ManyToOne
    @Column(nullable = false)
    private Boards boards;


}
