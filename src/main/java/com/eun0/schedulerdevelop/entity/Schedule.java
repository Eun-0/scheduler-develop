package com.eun0.schedulerdevelop.entity;

import com.eun0.schedulerdevelop.dto.schedule.ScheduleRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "schedules")
@NoArgsConstructor
public class Schedule extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(nullable = false, length = 15)
    private String title;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "schedule", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "schedule")
    private List<ScheduleManager> scheduleManagers = new ArrayList<>();

    public Schedule (String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public static Schedule of(ScheduleRequest requestDto, User user) {
        return new Schedule(requestDto.getTitle(), requestDto.getContent(), user);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
