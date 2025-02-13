package com.georgi.store.service.impl;


import com.georgi.store.model.dto.PageResponse;
import com.georgi.store.model.dto.gameDto.GameRequest;
import com.georgi.store.model.dto.gameDto.GameResponse;
import com.georgi.store.model.entity.Game;
import com.georgi.store.model.entity.Platform;
import com.georgi.store.model.enums.Console;
import com.georgi.store.model.mapper.GameMapper;
import com.georgi.store.repository.*;
import com.georgi.store.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;
    private final GameMapper gameMapper;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    private final WishListRepository wishListRepository;

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
        final List<Console> selectedConsoles = gameRequest.platforms()
                .stream()
                .map(Console::valueOf)
                .toList();

        final List<Platform> platforms = platformRepository.findAllByConsoleIn(selectedConsoles);

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
        game.setPlatforms(platforms);

        final Game savedGame = gameRepository.save(game);
        return savedGame.getId();
    }

    @Override
    public void updateGame(String gameId, GameRequest gameRequest) {
        final Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));


        if (!game.getTitle().equals(gameRequest.title()) && gameRepository.existsByTitle(gameRequest.title())) {
            log.info("Game with title already exists");
            //TODO create dedicated exception
            throw new RuntimeException("Title already exists");
        }

        final List<Console> selectedConsole = gameRequest.platforms()
                .stream()
                .map(Console::valueOf)
                .toList();

        // This is a common patter on how to manage a ManyToMany relation. How to add or remove relation between entities
        final List<Platform> platforms = platformRepository.findAllByConsoleIn(selectedConsole);

        if (platforms.size() != selectedConsole.size()) {
            log.warn("Received a non supported platforms. Received: {}", selectedConsole);
            throw new RuntimeException("One or more platforms are not supported");
        }


        final List<String> platformIds = platforms.stream()
                .map(Platform::getId)
                .toList();

        // Take the platforms that the game currently has
        List<Platform> currentPlatform = game.getPlatforms();

        // Fetch all the platforms  from the repository
        List <Platform> newPlatforms = platformRepository.findAllById(platformIds);

        // Take the all the supported platforms exp('PC', 'PS4', 'PS5' etc.)
        List<Platform> platformsToAdd = new ArrayList<>(newPlatforms);
        // Remove the platforms that are already saved
        platformsToAdd.removeAll(currentPlatform);


        List<Platform> platformsToRemove = new ArrayList<>(currentPlatform);
        platformsToRemove.removeAll(newPlatforms);

        for (Platform platform : platformsToAdd) {
            game.addPlatform(platform);
        }

        for (Platform platform: platformsToRemove){
            game.removePlatform(platform);
        }

        game.setTitle(gameRequest.title());
        gameRepository.save(game);
    }

    @Override
    public String uploadGameImg(MultipartFile file, String gameId) {
        return "";
    }

    @Override
    public PageResponse<GameResponse> findAllGames(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Game> gamesPage = gameRepository.findAll(pageable);

        List<GameResponse> gameResponses = gamesPage.stream()
                .map(gameMapper:: toGameResponse)
                .toList();
        
        
        return PageResponse.<GameResponse>builder()
                .content(gameResponses)
                .pageNumber(gamesPage.getNumber())
                .size(gamesPage.getSize())
                .totalElements(gamesPage.getTotalElements())
                .isFirst(gamesPage.isFirst())
                .isLast(gamesPage.isLast())
                .build();
    }

    @Override
    @Transactional
    public void deleteGame(String gameId, boolean confirm) {
        final List<String> warnings = new ArrayList<>();

        // check the comments
        long commentsCount = commentRepository.countByGameId(gameId);
        if (commentsCount > 0) {
            warnings.add("There is already a comment in the game");
            System.out.println("The current game has comments");
        }

        // check the wishlist
        long wishlistCount = wishListRepository.countByGameId(gameId);

        if (wishlistCount > 0) {
            warnings.add("There is already a wishlist in the game");
            System.out.println("The current game belongs to wishlist");
        }

        if (warnings.size() > 0) {
            // add custom exception
            throw new RuntimeException("One or more warnings are not supported");
        }


        // check the relation ( between Game Category Comment and Wishlist)
    }
}
