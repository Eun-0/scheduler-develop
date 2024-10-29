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
@Table(name = "schedules")   // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Schedule extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    @Column(nullable = false, length = 5)
    private String writer;

    @Column(nullable = false, length = 15)
    private String title;

    @Column(nullable = false, length = 200)
    private String content;

    public Schedule(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    @OneToMany(mappedBy = "schedule", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "schedule")
    private List<ScheduleManager> scheduleManagers =new ArrayList<>();


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
