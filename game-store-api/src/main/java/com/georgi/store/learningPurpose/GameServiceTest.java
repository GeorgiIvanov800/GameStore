package com.georgi.store.learningPurpose;

import com.georgi.store.model.entity.Game;
import com.georgi.store.model.enums.SupportedPlatforms;
import com.georgi.store.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceTest {

    private final GameRepository gameRepository;


    public void pagedResult() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Game> pagedResult = gameRepository.findAllByCategoryName("anyCat", pageable);


        gameRepository.findAll(pageable);
    }

    public PageResponse<Game> pagedResult(final int pageNumber, final int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "title"));

        Page<Game> pagedResult = gameRepository.findAllByCategoryName("anyCat", pageable);


        return PageResponse.<Game>builder()
                .content(pagedResult.getContent())
                .totalPages(pagedResult.getTotalPages())
                .totalElements(pagedResult.getNumberOfElements())
                .isFirst(pagedResult.isFirst())
                .isLast(pagedResult.isLast())
                .build();
    }

    // Use GameSpecification class to query the Database
    public void specificationExample1() {
        Specification<Game> spec = buildSpecificationWitchAndOperator("witcher", SupportedPlatforms.PC);

        List<Game> games = gameRepository.findAll(spec);
    }


    private void specificationExample2() {
        Specification<Game> spec = buildSpecificationWitchOrOperator("witcher", SupportedPlatforms.PC);

        List<Game> games = gameRepository.findAll(spec);
    }

    private Specification<Game> buildSpecificationWitchAndOperator (String title, SupportedPlatforms platform) {
        Specification<Game> spec = Specification.where(null);

        if (StringUtils.hasLength(title)) {
            spec = spec.and(GameSpecification.byGameTitle("witcher"));
        }

        if (platform != null) {
            spec = spec.and(GameSpecification.bySupportedPlatforms(SupportedPlatforms.PC));
        }

        return spec;
    }

    private Specification<Game> buildSpecificationWitchOrOperator (String title, SupportedPlatforms platform) {
        Specification<Game> spec = Specification.where(null);

        if (StringUtils.hasLength(title)) {
            spec = spec.or(GameSpecification.byGameTitle("witcher"));
        }

        if (platform != null) {
            spec = spec.or(GameSpecification.bySupportedPlatforms(SupportedPlatforms.PC));
        }

        return spec;
    }

    // 1 class (GameRepresentation1) (id, title, platform)
    // 2 call game repository and fetch all the games (paged)
    // 3 map the result  (loop over the result from the DB, do the mapping, collect, return the result)

    // With the interface representation we can skipp mapping
    public List<GameRepresentation1> getGamesWithRepresentation1() {
            return gameRepository.findAllGames();
    }
}
