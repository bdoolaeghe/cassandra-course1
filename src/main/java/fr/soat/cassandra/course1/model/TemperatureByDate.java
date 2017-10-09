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
@AllArgsConstructor
@Builder
@Table(name = "temperature_by_date")
public class TemperatureByDate {

    @PartitionKey
    @Column(name = "probe_date")
    private LocalDate probeDate;

    @ClusteringColumn
    @Column(name = "city")
    private String city;

    @Column(name = "temperature")
    private float temperature;

}
