package ru.skillbox.socialnetwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import ru.skillbox.socialnetwork.model.enumeration.FriendshipStatusCode;

@Entity
@Table(name = "friendship")
public class Friendship {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "src_person_id")
    @NotNull
    private Person srcPerson;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dst_person_id")
    @NotNull
    private Person dstPerson;

    @Enumerated(EnumType.STRING)
    @Column(name = "code", columnDefinition="ENUM('REQUEST', 'FRIEND', 'BLOCKED', 'DECLINED', 'SUBSCRIBED')")
    @NotNull
    private FriendshipStatusCode code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public FriendshipStatusCode getCode() {
        return code;
    }

    public void setCode(FriendshipStatusCode code) {
        this.code = code;
    }

}
