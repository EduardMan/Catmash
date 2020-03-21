package ru.mansurov.catmash.model;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mansurov.catmash.model.service.MashServiceImpl;

import javax.persistence.*;

@Entity
@Table(name = "mash", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Mash {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "message", nullable = false)
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
