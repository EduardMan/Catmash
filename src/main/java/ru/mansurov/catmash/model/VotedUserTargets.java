package ru.mansurov.catmash.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "voted_user_targets")
@IdClass(VotedUserTargets.class)
public class VotedUserTargets implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "target_id")
    private Target target;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_voted")
    private boolean isVoted;

    public VotedUserTargets() {
    }

    public VotedUserTargets(Target target, User user, boolean isVoted) {
        this.target = target;
        this.user = user;
        this.isVoted = isVoted;
    }

    public Target getTarget() {
        return target;
    }

    public User getUser() {
        return user;
    }

    public boolean isVoted() {
        return isVoted;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVoted(boolean voted) {
        isVoted = voted;
    }
}
