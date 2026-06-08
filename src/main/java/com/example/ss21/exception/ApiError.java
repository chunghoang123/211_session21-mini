package com.example.ss21.exception;

import java.time.LocalDateTime;

public record ApiError(LocalDateTime timestamp, int status, String message) {}
