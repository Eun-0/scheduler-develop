package com.eun0.schedulerdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "user")   // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    @Column(name = "username", nullable = false, length = 5)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @OneToMany(mappedBy = "user")
    private List<ScheduleManager> scheduleManagers =new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList =new ArrayList<>();

    public void update(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
