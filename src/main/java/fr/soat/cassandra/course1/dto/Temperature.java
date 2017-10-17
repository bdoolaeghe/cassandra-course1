package fr.soat.cassandra.course1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Temperature {
    private String city;
    private LocalDate date;
    private float temperature;
}
