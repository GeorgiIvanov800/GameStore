package com.georgi.store.service.impl;

import com.georgi.store.repository.GameRepository;
import com.georgi.store.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;


    @Override
    public void pagedResult() {
        Pageable pageable = PageRequest.of(0, 10);

        gameRepository.findAllByCategoryName("anyCat", pageable);
        gameRepository.findAll(pageable);
    }
}
