package ru.skillbox.socialnetwork.model;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * дружба
 */
@Entity
@Table(name = "friendship")
public class Friendship {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    /**
     * статус связи
     */
    @OneToMany(mappedBy = "id")
    private Set<FriendshipStatus> friendshipStatuses;

    /**
     *  пользователь, запросивший дружбу
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "src_person_id")
    @NotNull
    private Person srcPerson;

    /**
     * пользователь, получивший запрос
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dst_person_id")
    @NotNull
    private Person dstPerson;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<FriendshipStatus> getFriendshipStatuses() {
        return friendshipStatuses;
    }

    public void setFriendshipStatuses(
        Set<FriendshipStatus> friendshipStatuses) {
        this.friendshipStatuses = friendshipStatuses;
    }

    public Person getSrcPerson() {
        return srcPerson;
    }

    public void setSrcPerson(Person srcPerson) {
        this.srcPerson = srcPerson;
    }

    public Person getDstPerson() {
        return dstPerson;
    }

    public void setDstPerson(Person dstPerson) {
        this.dstPerson = dstPerson;
    }
}
