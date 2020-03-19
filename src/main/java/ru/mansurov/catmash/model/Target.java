package ru.mansurov.catmash.model;

import javax.persistence.*;

@Entity
@Table(name = "target")
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "mash_id")
    private Mash mash;
    @Column(name = "file_name")
    private String fileName;
    private int rating;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mash getMash() {
        return mash;
    }

    public void setMash(Mash mash) {
        this.mash = mash;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
