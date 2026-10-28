package com.opham.prepa.mapper.Rh;

import com.opham.prepa.model.Expedition.attrib_axe;
import com.opham.prepa.model.Rh.paramsMiseAdispo;
import org.springframework.jdbc.core.RowMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class paramsMiseAdispoMapper implements RowMapper<paramsMiseAdispo>{
    public paramsMiseAdispo mapRow(ResultSet rs, int rowNum) throws SQLException {
        paramsMiseAdispo cmd = new paramsMiseAdispo();
        cmd.setDep(rs.getString(1));
        cmd.setNom_dep(rs.getString(2));
        cmd.setCode_poste(rs.getString(3));
        cmd.setNom_poste(rs.getString(4));
        cmd.setAscoma_assurance(rs.getInt(5));
        cmd.setCasier_casier(rs.getInt(6));
        cmd.setBlouse_epi(rs.getInt(7));
        cmd.setCasque_epi(rs.getInt(8));
        cmd.setChaussure_epi(rs.getInt(9));
        cmd.setCombinaison_epi(rs.getInt(10));
        cmd.setDoudoune_epi(rs.getInt(11));
        cmd.setGant_epi(rs.getInt(12));
        cmd.setGilet_epi(rs.getInt(13));
        cmd.setImpermeable_epi(rs.getInt(14));
        cmd.setLaptop_mat_info(rs.getInt(15));
        cmd.setPc_bureau_mat_info(rs.getInt(16));
        cmd.setTablette_mat_info(rs.getInt(17));
        cmd.setCarte_carburant_roulant(rs.getInt(18));
        cmd.setMoto_roulant(rs.getInt(19));
        cmd.setVehicule_roulant(rs.getInt(20));
        cmd.setAppel_telephonique(rs.getInt(21));
        cmd.setData_telephonique(rs.getInt(22));
        cmd.setPoste_telephonique(rs.getInt(23));

        return cmd;
    }
}
