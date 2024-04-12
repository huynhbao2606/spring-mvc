package com.bao.crm.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaginationParams {
    int pageNumber = 1;
    int pageSize = 10;
}
