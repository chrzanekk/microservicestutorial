package com.konradchrzanowski.customer.payload;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
