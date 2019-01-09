package com.hk3t.service;

import com.hk3t.model.Human;
import com.hk3t.repository.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class HumanServiceImpl implements HumanService {
    @Autowired
    private HumanRepository humanRepository;

    @Override
    public Page <Human> findAll(Pageable pageable) {
        return humanRepository.findAll(pageable);
    }

    @Override
    public Page <Human> findAllByNameContaining(String name, Pageable pageable) {
        return humanRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Human findById(Long id) {
        return humanRepository.findOne(id);
    }

    @Override
    public void save(Human human) {
        humanRepository.save(human);
    }

    @Override
    public void remove(Long id) {
        humanRepository.delete(id);
    }
}
