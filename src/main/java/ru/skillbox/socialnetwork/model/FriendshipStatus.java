package ru.skillbox.socialnetwork.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import ru.skillbox.socialnetwork.model.enumeration.CodeFriendshipStatus;

/**
 * статус дружбы
 */
@Entity
@Table(name = "friendship_status")
public class FriendshipStatus {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column(name = "time")
    private Date time;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "friendshipStatus")
    private Friendship friendship;

    /**
     * код статуса
     *
     * REQUEST - Запрос на добавление в друзья
     * FRIEND - Друзья
     * BLOCKED - Пользователь в чёрном списке
     * DECLINED - Запрос на добавление в друзья отклонён
     * SUBSCRIBED - Подписан
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "code", columnDefinition="ENUM('REQUEST', 'FRIEND', 'BLOCKED', 'DECLINED', 'SUBSCRIBED')")
    @NotNull
    private CodeFriendshipStatus code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CodeFriendshipStatus getCode() {
        return code;
    }

    public void setCode(CodeFriendshipStatus code) {
        this.code = code;
    }

    public Friendship getFriendship() {
        return friendship;
    }

    public void setFriendship(Friendship friendship) {
        this.friendship = friendship;
    }
}
