package ru.skillbox.socialnetwork.model;

import ru.skillbox.socialnetwork.model.enumeration.CodeFriendshipStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * статус дружбы
 */
@Entity
public class FriendshipStatus {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    private int id;

    /**
     * дата и время установки данного статуса
     */
    @Column(name = "time")
    private Date time;

    /**
     * имя
     */
    @Column(name = "name")
    private String name;

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
}
