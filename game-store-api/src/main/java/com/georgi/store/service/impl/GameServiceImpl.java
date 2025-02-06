package com.georgi.store.service.impl;

import com.georgi.store.repository.GameRepository;
import com.georgi.store.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;


}
