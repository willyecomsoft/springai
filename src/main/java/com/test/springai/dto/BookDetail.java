package com.test.springai.dto;

public record BookDetail(
    int year,
    String category,
    String book,
    String review,
    String author,
    String summary
) {}
