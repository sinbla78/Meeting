package com.example.decofolio.domain.meeting.domain.repository;

import com.example.decofolio.domain.meeting.domain.Meeting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    /**
     * 제목을 포함하는 모임 목록을 찾는 메서드
     *
     * @param title 제목
     * @return 제목을 포함한 모임 목록
     */
    Page<Meeting> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
