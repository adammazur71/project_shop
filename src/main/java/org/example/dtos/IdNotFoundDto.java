package org.example.dtos;

import org.springframework.http.HttpStatus;

public record IdNotFoundDto(String message, HttpStatus status) {
}
