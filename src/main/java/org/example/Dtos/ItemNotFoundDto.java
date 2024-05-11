package org.example.Dtos;

import org.springframework.http.HttpStatus;

public record ItemNotFoundDto(String message, HttpStatus status) {
}
