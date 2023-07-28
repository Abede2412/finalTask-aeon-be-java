package com.example.demo.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paging {

    private Integer currentPage;

    private Integer totalPage;

    private Integer size;
}
