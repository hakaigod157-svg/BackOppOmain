package com.opham.prepa.model.Expedition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trans_facture implements Serializable {
        private String code;
        private String facture;
        private BigDecimal montant;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date date;
        private String transport;

}
