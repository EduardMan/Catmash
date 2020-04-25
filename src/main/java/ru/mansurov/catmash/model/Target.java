package ru.mansurov.catmash.model;

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
    @JoinColumn(name = "name")
    private String name;

    // @JsonBackReference
    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL)
    private Set<VotedUserTargets> votedUsers;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<VotedUserTargets> getVotedUsers() {
        return votedUsers;
    }

    public void setVotedUsers(Set<VotedUserTargets> votedUsers) {
        this.votedUsers = votedUsers;
    }
}
