package com.opham.prepa.model.ValidationCommande;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CommandeValiderStats implements Serializable {
    private String user_valid;
    private BigDecimal nb;
    private String percent;
}
