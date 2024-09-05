package emazon.user.domain.api;

import emazon.user.ports.persistence.mysql.entity.UserEntity;
import io.jsonwebtoken.Claims;


import java.security.Key;
import java.util.Map;

public interface IJwtServicePort {

    public String generateToken(UserEntity user, Map<String, Object> extraClaims) {


    }

    private Key generateKey(){

    }

    public String extractUsername(String jwt) {

    }

    private Claims extractAllClaims(String jwt) {

    }
}
