package com.georgi.store.service;


import com.georgi.store.model.dto.gameDto.GameRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface GameService {

    String saveGame(GameRequest game);

    void updateGame(String gameId, GameRequest gameRequest);

    String uploadGameImg(MultipartFile file, String gameId);

    Page findAllGames(Pageable pageable);



}
