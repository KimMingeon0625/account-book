package com.accountbook.dto.category;

import com.accountbook.domain.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * CategoryRequest
 *
 * @author donggun
 * @since 2021/11/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @NotEmpty
    private String userId;

    @NotEmpty(message = "카테고리명 누락")
    private String name;

    @NotEmpty(message = "자산타입 누락")
    private EventType eventType;

    @NotEmpty(message = "사용여부 누락")
    private Boolean useYn;

}
