package com.project.playlist.domain.playlist.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    POP("Pop"),
    KPOP("K-Pop"),
    CLASSIC("클래식"),
    HIPHOP("힙합"),
    OST("OST"),
    BALLADE("발라드");


    private String type;
}
