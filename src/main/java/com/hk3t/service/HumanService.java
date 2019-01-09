package com.hk3t.service;

import com.hk3t.model.Human;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HumanService {
    Page <Human> findAll(Pageable pageable);

    Page <Human> findAllByNameContaining(String name, Pageable pageable);

    Human findById(Long id);

    void save(Human human);

    void remove(Long id);
}
