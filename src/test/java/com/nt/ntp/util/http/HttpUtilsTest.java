package com.nt.ntp.util.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.UnknownHostException;

class HttpUtilsTest {
    // valid
    static final String VALID_URL = "http://fruit.api.postype.net";
    static final String VALID_PATH = "token";
    URI validUri = UriComponentsBuilder.fromUriString(VALID_URL).path(VALID_PATH).encode().build().toUri();
    Request validRequest = new Request(validUri);

    // invalid
    static final String INVALID_URL = "http://invalid.url.com";
    static final String INVALID_PATH = "invalid";
    URI invalidUri = UriComponentsBuilder.fromUriString(INVALID_URL).path(INVALID_PATH).encode().build().toUri();
    Request invalidRequest = new Request(invalidUri);

    // empty
    static final String EMPTY = "";
    Request emptyRequest = new Request(UriComponentsBuilder.fromUriString(EMPTY).build().toUri(), RequestMethod.GET, null);

    @Nested
    class connect {

        @Nested
        class RequestParameter {

            @Nested
            class URIParameter {

                @Test
                void Null() {
                    // Given
                    // When
                    // Then
                    Assertions.assertThrows(NullPointerException.class, () -> HttpUtils.connect(null));
                }

                @Test
                void empty() {
                    // Given
                    // When
                    // Then
                    Assertions.assertThrows(IllegalArgumentException.class, () -> HttpUtils.connect(emptyRequest));
                }

                @Test
                void invalid() {
                    // Given
                    // When
                    // Then
                    Assertions.assertThrows(UnknownHostException.class, () -> HttpUtils.connect(invalidRequest));
                }

                @Test
                void valid() {
                    // Given
                    // When
                    // Then
                    Assertions.assertDoesNotThrow(() -> HttpUtils.connect(validRequest));
                }
            }

            @Nested
            class MethodParameter {

                @Test
                void Null() {
                    // Given
                    Request methodNullRequest = new Request(validUri, null, null);
                    // When
                    // Then
                    Assertions.assertThrows(NullPointerException.class, () -> HttpUtils.connect(methodNullRequest));
                }

                @Test
                void valid() {
                    // Given
                    // When
                    // Then
                    Assertions.assertDoesNotThrow(() -> HttpUtils.connect(validRequest));
                }
            }
        }
    }
}