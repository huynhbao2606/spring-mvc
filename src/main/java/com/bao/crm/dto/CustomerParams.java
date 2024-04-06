package com.bao.crm.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerParams {
    String search;
    String sort;
    int pageNumber = 1;
    int pageSize = 10;
}
