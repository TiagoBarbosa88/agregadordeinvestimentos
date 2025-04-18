package com.projetoJava.agregadordeinvestimentos.controller;

public record CreateUserDto(
        String username,
        String email,
        String password
) {
}
