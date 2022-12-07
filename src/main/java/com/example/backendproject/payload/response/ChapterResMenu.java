package com.example.backendproject.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ChapterResMenu {
    private Long id;
    private String name;

    private Long parentId;
    private List<ChapterResMenu> sousChapitres;
}
