package com.eun0.schedulerdevelop.service;

import com.eun0.schedulerdevelop.dto.schedule.SchedulePagingResponse;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleRequest;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleResponse;
import com.eun0.schedulerdevelop.entity.Schedule;
import com.eun0.schedulerdevelop.entity.User;
import com.eun0.schedulerdevelop.repository.ScheduleRepository;
import com.eun0.schedulerdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    UserRepository userRepository;
    ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponse createSchedule(ScheduleRequest requestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 DB에 존재하지 않습니다."));

        Schedule savedSchedule = scheduleRepository.save(Schedule.of(requestDto, user));

        return ScheduleResponse.from(savedSchedule);
    }

    @Transactional
    public ScheduleResponse readSchedule(Long id) {
        Schedule readSchedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일정이 DB에 존재하지 않습니다."));

        return ScheduleResponse.from(readSchedule);
    }

    @Transactional
    public Page<SchedulePagingResponse> readSchedulesAsPageSize(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Schedule> schedules = scheduleRepository.findAllByOrderByModifiedAtDesc(pageable);
        return schedules.map(SchedulePagingResponse::from);
    }

    @Transactional
    public ScheduleResponse updateSchedule(Long id, ScheduleRequest requestDto, Long userId) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 스케줄이 DB에 존재하지 않습니다."));

        if (userId.equals(schedule.getUser().getId())) {
            throw new IllegalArgumentException("해당 일정의 작성자가 아닙니다.");
        }

        schedule.update(requestDto.getTitle(), requestDto.getContent());

        return ScheduleResponse.from(schedule);
    }


    @Transactional
    public void deleteSchedule(Long id, Long userId) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 스케줄이 DB에 존재하지 않습니다."));

        if (userId.equals(schedule.getUser().getId())) {
            throw new IllegalArgumentException("해당 일정의 작성자가 아닙니다.");
        }

        scheduleRepository.delete(schedule);
    }
}
