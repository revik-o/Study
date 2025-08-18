package com.data.ox.core.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Getter
@RequiredArgsConstructor
public final class Page<T extends Serializable> implements Serializable {

    private final int maxSize;
    private final int totalPages;
    private final Collection<T> collection;
}
