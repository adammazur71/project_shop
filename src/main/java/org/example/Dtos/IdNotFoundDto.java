package org.example.Dtos;

import org.springframework.http.HttpStatus;

public record IdNotFoundDto(String message, HttpStatus status) {
}
