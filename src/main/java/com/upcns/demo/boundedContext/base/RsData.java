package com.upcns.demo.boundedContext.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData {
    private final String code;
    private final String formatted;
    public static RsData of(String code, String formatted) {
        return new RsData(code,formatted);
    }
}
