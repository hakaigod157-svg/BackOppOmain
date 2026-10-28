package com.opham.prepa.model.Catalogue;

import lombok.*;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class listLabo implements Serializable{
    private String focode;
    private String fonom;
}
