package fr.soat.cassandra.course1.model;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "temperature_by_city")
public class TemperatureByCity {

    @PartitionKey
    @Column(name = "city")
    private String city;

    @ClusteringColumn
    @Column(name = "probe_date")
    private LocalDate probeDate;

    @Column(name = "temperature")
    private float temperature;

}
