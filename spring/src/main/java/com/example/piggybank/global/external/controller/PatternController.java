package com.example.piggybank.global.external.controller;

import com.example.piggybank.global.external.dto.req.BatchRequest;
import com.example.piggybank.global.external.dto.resp.BatchResponse;
import com.example.piggybank.global.external.service.PatternAnalysisService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pattern")
@RequiredArgsConstructor
public class PatternController {

    private final PatternAnalysisService patternAnalysisService;

    @PostMapping("/analyze")
    public BatchResponse analyze(@RequestBody BatchRequest request) {

        return patternAnalysisService.analyzeDescriptions(request.items());
    }

}
