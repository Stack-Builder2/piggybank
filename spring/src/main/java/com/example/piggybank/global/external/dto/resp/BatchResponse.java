package com.example.piggybank.global.external.dto.resp;

import java.util.List;

public record BatchResponse(
    List<BatchResponseItem> items
) {

}
