package com.accountbook.dto.user;

import com.accountbook.domain.entity.Category;
import com.accountbook.domain.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String name;
    private EventType eventType;

    public CategoryDto(Category category) {
        this.name = category.getComCategory().getName();
        this.eventType = category.getComCategory().getEventType();
    }
}