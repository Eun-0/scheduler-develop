package com.eun0.schedulerdevelop.service;

import com.eun0.schedulerdevelop.dto.schedule.SchedulePagingResponse;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleRequest;
import com.eun0.schedulerdevelop.dto.schedule.ScheduleResponse;
import com.eun0.schedulerdevelop.entity.Schedule;
import com.eun0.schedulerdevelop.entity.User;
import com.eun0.schedulerdevelop.exception.ApplicationException;
import com.eun0.schedulerdevelop.exception.ErrorCode;
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
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponse createSchedule(ScheduleRequest requestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        Schedule savedSchedule = scheduleRepository.save(Schedule.of(requestDto, user));

        return ScheduleResponse.from(savedSchedule);
    }

    @Transactional
    public ScheduleResponse readSchedule(Long id) {
        Schedule readSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.SCHEDULE_NOT_FOUND));

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
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.SCHEDULE_NOT_FOUND));

        if (!userId.equals(schedule.getUser().getId())) {
            throw new ApplicationException(ErrorCode.SCHEDULE_NO_PERMISSION);
        }

        schedule.update(requestDto.getTitle(), requestDto.getContent());

        return ScheduleResponse.from(schedule);
    }


    @Transactional
    public void deleteSchedule(Long id, Long userId) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.SCHEDULE_NOT_FOUND));

        if (!userId.equals(schedule.getUser().getId())) {
            throw new ApplicationException(ErrorCode.SCHEDULE_NO_PERMISSION);
        }

        scheduleRepository.delete(schedule);
    }
}
