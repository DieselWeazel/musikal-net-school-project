package com.example.demo.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class MusicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "entity_title")
    private String entityTitle;
    //  @OneToOne
//  private Description description;
    private String description;

    public MusicEntity() {
    }

    public MusicEntity(String entityTitle, String description) {
        this.entityTitle = entityTitle;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityTitle() {
        return entityTitle;
    }

    public void setEntityTitle(String entityTitle) {
        this.entityTitle = entityTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
