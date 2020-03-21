package ru.mansurov.catmash.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

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
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Column(nullable = false)
    @JoinColumn(name = "rating")
    private int rating;
    @Column(nullable = false)
    @JoinColumn(name = "name")
    private String name;

//    @ManyToMany(mappedBy = "votedTargets")
//    @JsonManagedReference
//    private Set<User> votedUsers;

    @ManyToMany
    @JoinTable(
            name = "voted_user_targets",
            joinColumns = @JoinColumn(name = "target_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonBackReference
    private Set<User> votedUsers;

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

    public Set<User> getVotedUsers() {
        return votedUsers;
    }

    public void setVotedUsers(Set<User> votedUsers) {
        this.votedUsers = votedUsers;
    }
}
