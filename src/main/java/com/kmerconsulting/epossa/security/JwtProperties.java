package com.kmerconsulting.epossa.security;

public class JwtProperties {

    public static final String SECRET = "epossa_secret";
    public static final int EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
