package com.example.piggybank.global.external.dto.req;

import java.util.List;

public record BatchRequest(
    List<TxnRequest> items
) {

}
