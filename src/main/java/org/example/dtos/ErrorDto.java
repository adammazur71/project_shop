package org.example.dtos;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public record ErrorDto(String message, HttpStatus status) {
}
