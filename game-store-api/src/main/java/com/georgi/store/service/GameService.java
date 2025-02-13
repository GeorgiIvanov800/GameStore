package com.georgi.store.service;


import com.georgi.store.model.dto.PageResponse;
import com.georgi.store.model.dto.gameDto.GameRequest;
import com.georgi.store.model.dto.gameDto.GameResponse;
import org.springframework.web.multipart.MultipartFile;

public interface GameService {

    String saveGame(GameRequest game);

    void updateGame(String gameId, GameRequest gameRequest);

    String uploadGameImg(MultipartFile file, String gameId);

    PageResponse<GameResponse> findAllGames(int page, int size);

    void deleteGame(String gameId, boolean confirm);

}
