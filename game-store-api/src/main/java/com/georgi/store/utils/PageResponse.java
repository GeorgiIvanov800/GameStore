package com.georgi.store.utils;

import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {

    private List<T> content;
    private int totalElements;
    private int totalPages;
    private boolean isLast;
    private boolean isFirst;
}
