package com.georgi.store.service.impl;


import com.georgi.store.model.dto.PageResponse;
import com.georgi.store.model.dto.gameDto.GameRequest;
import com.georgi.store.model.dto.gameDto.GameResponse;
import com.georgi.store.model.entity.Game;
import com.georgi.store.model.entity.Platform;
import com.georgi.store.model.enums.Console;
import com.georgi.store.model.mapper.GameMapper;
import com.georgi.store.repository.CategoryRepository;
import com.georgi.store.repository.GameRepository;
import com.georgi.store.repository.PlatformRepository;
import com.georgi.store.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;
    private final GameMapper gameMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public String saveGame(final GameRequest gameRequest) {
        // option 1: loop over the list of platforms and fetch them one by one from the DB
        // select * from platform where console = ('PC', 'PS', 'XBOX')


        if (gameRepository.existsByTitle(gameRequest.title())) {
            log.info("Game with title already exists");
            //TODO create dedicated exception
            throw new RuntimeException("Title already exists");
        }


        // option 2: mapping the platforms (request) to Platform (entity) and fetch all of them at once
        // select * from platform where console in ('PC', 'PS', 'XBOX')
        final Set<Console> selectedConsoles = gameRequest.platforms()
                .stream()
                .map(Console::valueOf)
                .collect(Collectors.toSet());

        final Set<Platform> platforms = platformRepository.findAllByConsoleIn(selectedConsoles);

        if (platforms.size() != selectedConsoles.size()) {
            log.warn("Received a non supported platforms. Received: {} - Stored: {}", selectedConsoles, platforms);
            //TODO create dedicated exception
            throw new RuntimeException("One or more platforms are not supported");

        }

        if (!categoryRepository.existsById(gameRequest.categoryId())) {
            log.warn("Received a category that does not exists: {}", gameRequest.categoryId());
            throw new RuntimeException("Category does not exist");
        }


        final Game game = gameMapper.toGame(gameRequest);
        game.setPlatforms((List<Platform>) platforms);

        final Game savedGame = gameRepository.save(game);
        return savedGame.getId();
    }

    @Override
    public void updateGame(String gameId, GameRequest gameRequest) {

    }

    @Override
    public String uploadGameImg(MultipartFile file, String gameId) {
        return "";
    }

    @Override
    public PageResponse<GameResponse> findAllGames(int page, int size) {
        return null;
    }

    @Override
    public void deleteGame(String gameId) {

    }
}
