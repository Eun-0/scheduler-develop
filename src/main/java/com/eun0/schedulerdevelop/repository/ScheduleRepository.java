package com.eun0.schedulerdevelop.repository;

import com.eun0.schedulerdevelop.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findAllByOrderByModifiedAtDesc(Pageable pageable);
}
