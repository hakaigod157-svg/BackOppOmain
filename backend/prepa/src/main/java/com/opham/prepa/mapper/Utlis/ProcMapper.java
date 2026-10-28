package com.opham.prepa.mapper.Utlis;

import com.opham.prepa.model.Utils.Proc;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class ProcMapper  implements RowMapper<Proc>{
    public Proc mapRow(ResultSet rs, int rowNum) throws SQLException{
        Proc pro = new Proc();

        pro.setEnumCode(rs.getString("ENUM_CODE"));
        pro.setEnumLibelle(rs.getString("ENUM_LIBELLE"));
        pro.setEnumType(rs.getString("ENUM_TYPE"));

        return pro;
    }
}
