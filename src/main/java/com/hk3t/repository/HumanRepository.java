package com.hk3t.repository;

import com.hk3t.model.Human;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HumanRepository extends PagingAndSortingRepository <Human, Long> {
    Page <Human> findAllByNameContaining(String name, Pageable pageable);
}
