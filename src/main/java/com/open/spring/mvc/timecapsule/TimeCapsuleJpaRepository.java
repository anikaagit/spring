package com.open.spring.mvc.timecapsule;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TimeCapsuleJpaRepository extends JpaRepository<TimeCapsule, Long> {
    List<TimeCapsule> findByAuthor(String author);
    List<TimeCapsule> findByIsOpened(boolean isOpened);
}