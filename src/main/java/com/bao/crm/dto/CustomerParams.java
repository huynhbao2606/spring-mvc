package com.bao.crm.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerParams extends PaginationParams{
    String search;
    String sort;
}
