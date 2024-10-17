package com.eun0.schedulerdevelop.service;

import com.eun0.schedulerdevelop.dto.schedule.SchedulePagingResponse;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleRequest;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleResponse;
import com.eun0.schedulerdevelop.entity.Schedule;
import com.eun0.schedulerdevelop.repository.ScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {
    ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponse createSchedule(ScheduleRequest requestDto) {
        // RequestDTO -> Entity
        Schedule schedule = requestDto.toEntity();

        // DB 저장
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDTO
        return ScheduleResponse.from(savedSchedule);
    }

    public ScheduleResponse readScheduleById(Long id) {
        // 해당 일정이 DB에 존재하는지 확인
        Schedule readSchedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일정이 DB에 존재하지 않습니다."));

        // Entity -> ResponseDTO
        return ScheduleResponse.from(readSchedule);
    }

    public Page<SchedulePagingResponse> readSchedulesAsPageSize(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Schedule> schedules = scheduleRepository.findAllByOrderByModifiedAtDesc(pageable);
        return schedules.map(SchedulePagingResponse::from);
    }

    @Transactional
    public ScheduleResponse updateSchedule(Long id, ScheduleRequest requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 스케줄이 DB에 존재하지 않습니다."));

        // RequestDTO -> Entity
        schedule.update(requestDto.getWriter(), requestDto.getTitle(), requestDto.getContent());

        // Entity -> ResponseDTO
        return ScheduleResponse.from(schedule);
    }

    public void deleteSchedule(Long id) {
        // 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 스케줄이 DB에 존재하지 않습니다."));

        scheduleRepository.delete(schedule);
    }
}
