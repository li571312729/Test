package com.designMode.责任链结合策略.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Condition {

    private String type;
    private boolean open;
    // 条件描述
    private String desc;
}
