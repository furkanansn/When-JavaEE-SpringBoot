package com.Hobedtech.when.repository;

import com.Hobedtech.when.dto.CheckInDto;
import com.Hobedtech.when.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn,Long> {
    List<CheckIn> getCheckInByEvents_IdAndUsers_IdNot(Long eventId,Long userId);
}
