package org.example.dtos;

import org.springframework.http.HttpStatus;

public record ItemNotFoundDto(String message, HttpStatus status) {
}
