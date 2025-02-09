package com.georgi.store.service.impl;

import com.georgi.store.model.entity.Game;
import com.georgi.store.model.enums.SupportedPlatforms;
import com.georgi.store.repository.GameRepository;
import com.georgi.store.service.GameService;
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
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;


    @Override
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
    public void specificationExample() {
        Specification<Game> spec = buildSpecificationWitchAndOperator("witcher", SupportedPlatforms.PC);

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
}
