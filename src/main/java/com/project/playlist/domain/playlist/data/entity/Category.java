package com.project.playlist.domain.playlist.data.entity;

import java.util.*;
import java.util.stream.Collectors;

public enum Category {

    ROOT("카테고리", null),
        CATEGORY("음악 장르", ROOT),
            POP("팝",CATEGORY),
            KPOP("케이팝",CATEGORY),
            CLASSIC("클래식",CATEGORY),
            HIPHOP("힙합",CATEGORY),
            OST("OST",CATEGORY),
            BALLADE("발라드",CATEGORY);




    private final String title;
    private final Category parentCategory;
    private final List<Category> childCategories;


    Category(String title, Category parentCategory) {
        this.childCategories = new ArrayList<>();
        this.title = title;
        this.parentCategory = parentCategory;
        if(Objects.nonNull(parentCategory)) {
            parentCategory.childCategories.add(this);
        }
    }

    public String getTitle() {
        return title;
    }

    // 부모카테고리 Getter
    public Optional<Category> getParentCategory() {
        return Optional.ofNullable(parentCategory);
    }

    // 자식카테고리 Getter
    public List<Category> getChildCategories() {
        return Collections.unmodifiableList(childCategories);
    }

    // 마지막 카테고리(상품추가 가능)인지 반환
    public boolean isLeafCategory() {
        return childCategories.isEmpty();
    }

    // 마지막 카테고리(상품추가 가능)들 반환
    public List<Category> getLeafCategories() {
        return Arrays.stream(Category.values())
                .filter(category -> category.isLeafCategoryOf(this))
                .collect(Collectors.toList());
    }

    private boolean isLeafCategoryOf(Category category) {
        return this.isLeafCategory() && category.contains(this);
    }

    private boolean contains(Category category) {
        if(this.equals(category)) return true;

        return Objects.nonNull(category.parentCategory) &&
                this.contains(category.parentCategory);
    }

}
