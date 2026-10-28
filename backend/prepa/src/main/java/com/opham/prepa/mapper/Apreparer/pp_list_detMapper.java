package com.opham.prepa.mapper.Apreparer;


import com.opham.prepa.model.Apreparer.pp_list_det;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class pp_list_detMapper implements RowMapper<pp_list_det> {
    public pp_list_det mapRow(ResultSet rs, int rowNum) throws SQLException {
        pp_list_det cmd = new pp_list_det();
        cmd.setPpL_article(rs.getString(1));
        cmd.setPpL_libelle(rs.getString(2));
        cmd.setPpL_qte(rs.getInt(3));
        cmd.setPpL_lot(rs.getString(4));
        cmd.setPpL_depot(rs.getString(5));
        cmd.setPpL_empl(rs.getString(6));
        cmd.setPpL_offert(rs.getInt(7));
        cmd.setPpL_code(rs.getString(8));
        return cmd;
    }
}
