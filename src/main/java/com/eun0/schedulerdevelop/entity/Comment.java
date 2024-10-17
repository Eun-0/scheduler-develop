package com.eun0.schedulerdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment
    private Long id;

    @Column(name = "content", nullable = false, length = 20)
    private String content;

    @Column(name = "writer", nullable = false, length = 5)
    private String writer;

    public Comment(String content, String writer) {
        this.content = content;
        this.writer = writer;
    }

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void update(String content) {
        this.content = content;
    }
}
