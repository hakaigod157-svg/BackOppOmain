package com.opham.prepa.mapper.Expedition;
import com.opham.prepa.model.Expedition.livraison;
import com.opham.prepa.model.Expedition.livraison_opham;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class livraison_ophamMapper implements RowMapper<livraison_opham>{

    public livraison_opham mapRow(ResultSet rs, int rowNum) throws SQLException {
        livraison_opham cmd = new livraison_opham();
        cmd.setVague(rs.getString(1));
        cmd.setHeure(rs.getTime(2));

        cmd.setSans_axe_cde(rs.getInt(3));
        cmd.setSans_axe_colis(rs.getInt(4));
        cmd.setSans_axe_couleur(rs.getInt(5));

        cmd.setAnosy_cde(rs.getInt(6));
        cmd.setAnosy_colis(rs.getInt(7));
        cmd.setAnosy_couleur(rs.getInt(8));

        cmd.setCentre_cde(rs.getInt(9));
        cmd.setCentre_colis(rs.getInt(10));
        cmd.setCentre_couleur(rs.getInt(11));

        cmd.setEst_meteo_cde(rs.getInt(12));
        cmd.setEst_meteo_colis(rs.getInt(13));
        cmd.setEst_meteo_couleur(rs.getInt(14));

        cmd.setEst_rn2_cde(rs.getInt(15));
        cmd.setEst_rn2_colis(rs.getInt(16));
        cmd.setEst_rn2_couleur(rs.getInt(17));

        cmd.setItaosy_cde(rs.getInt(18));
        cmd.setItaosy_colis(rs.getInt(19));
        cmd.setItaosy_couleur(rs.getInt(20));

        cmd.setItaosy_rn1_cde(rs.getInt(21));
        cmd.setItaosy_rn1_colis(rs.getInt(22));
        cmd.setItaosy_rn1_couleur(rs.getInt(23));

        cmd.setIvato_cde(rs.getInt(24));
        cmd.setIvato_colis(rs.getInt(25));
        cmd.setIvato_couleur(rs.getInt(26));

        cmd.setMahitsy_rn4_cde(rs.getInt(27));
        cmd.setMahitsy_rn4_colis(rs.getInt(28));
        cmd.setMahitsy_rn4_couleur(rs.getInt(29));

        cmd.setNord_cde(rs.getInt(30));
        cmd.setNord_colis(rs.getInt(31));
        cmd.setNord_couleur(rs.getInt(32));

        cmd.setSixsept_cde(rs.getInt(33));
        cmd.setSixsept_colis(rs.getInt(34));
        cmd.setSixsept_couleur(rs.getInt(35));

        cmd.setSud_cde(rs.getInt(36));
        cmd.setSud_colis(rs.getInt(37));
        cmd.setSud_couleur(rs.getInt(38));

        cmd.setSud_est_cde(rs.getInt(39));
        cmd.setSud_est_colis(rs.getInt(40));
        cmd.setSud_est_couleur(rs.getInt(41));

        cmd.setTotal_cde(rs.getInt(42));
        cmd.setTotal_colis(rs.getInt(43));
        cmd.setTotal_couleur(rs.getInt(44));

        return cmd;
    }
}
