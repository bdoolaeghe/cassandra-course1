package fr.soat.cassandra.course1.model;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@Table(name = "temperature_by_date")
public class TemperatureByDate {

    // Q2.9
    public TemperatureByDate(LocalDate date, String city, float temperature) {
        throw new RuntimeException("implement me !");
    }

}
