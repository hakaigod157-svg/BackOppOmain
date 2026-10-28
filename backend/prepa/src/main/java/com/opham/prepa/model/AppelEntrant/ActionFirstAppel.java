package com.opham.prepa.model.AppelEntrant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ActionFirstAppel implements Serializable{
    private int id;
    private String name;
    private int resolu;
    private int visible_btn;
    private boolean is_status;
}
