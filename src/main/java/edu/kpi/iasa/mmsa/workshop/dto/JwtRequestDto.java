package edu.kpi.iasa.mmsa.workshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtRequestDto {
    String login;
    String password;
}
