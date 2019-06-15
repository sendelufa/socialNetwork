package ru.skillbox.socialnetwork.model;

import javax.persistence.*;

/**
 * дружба
 */
@Entity
public class Friendship {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * статус связи
     */
    @Column(name = "status_id")
    private int statusId;

    /**
     *  пользователь, запросивший дружбу
     */
    @Column(name = "src_person_id")
    private int srcPersonId;

    /**
     * пользователь, получивший запрос
     */
    @Column(name = "dst_person_id")
    private int dstPersonId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getSrcPersonId() {
        return srcPersonId;
    }

    public void setSrcPersonId(int srcPersonId) {
        this.srcPersonId = srcPersonId;
    }

    public int getDstPersonId() {
        return dstPersonId;
    }

    public void setDstPersonId(int dstPersonId) {
        this.dstPersonId = dstPersonId;
    }
}
