package com.opham.prepa.model.Maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voiture implements Serializable{
    private String VOIT_NUM;


    private int VOIT_KM;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp DATE;

    public boolean isEmpty() {
        return false;
    }
}
