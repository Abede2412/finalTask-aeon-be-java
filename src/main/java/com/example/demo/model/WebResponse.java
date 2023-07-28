package com.example.demo.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebResponse<T>{

    private T data;

    private Paging paging;

    private String errors;
}
