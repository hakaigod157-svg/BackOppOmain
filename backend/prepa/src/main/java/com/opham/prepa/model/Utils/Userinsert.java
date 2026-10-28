package com.opham.prepa.model.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userinsert implements Serializable {
    private String ncpsuserusercode;
    private String ncpsuserncpscode;
}
