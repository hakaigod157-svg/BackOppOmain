package com.opham.prepa.mapper.genererBP;

import com.opham.prepa.model.genererBP.ArticleCmd;
import com.opham.prepa.model.genererBP.ListeCmd;
import com.opham.prepa.model.genererBP.RayonCmd;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListeCmdMapper implements RowMapper<ListeCmd> {
    @Override
    public ListeCmd mapRow(ResultSet rs, int rowNum) throws SQLException {
        ListeCmd cmd = new ListeCmd();
        cmd.setArticle(rs.getString(1));
        cmd.setLettre(rs.getString(2));
        cmd.setDesignation(rs.getString(3));
        cmd.setLienCode(rs.getString(4));
        cmd.setLienNum(rs.getInt(5));
        cmd.setQte(rs.getInt(6));
        cmd.setUnitfact(rs.getInt(7));
        cmd.setPrix_ht(rs.getBigDecimal(8));
        cmd.setModeliv(rs.getString(9));
        cmd.setLignelibre(rs.getString(10));
        cmd.setTypeVente(rs.getString(11));
        cmd.setReglement(rs.getInt(12));
        cmd.setEcheancesp(rs.getInt(13));
        cmd.setAbs_qte(rs.getInt(14));
        cmd.setFactman(rs.getInt(15));
        cmd.setOffert(rs.getInt(16));
        cmd.setArtype(rs.getInt(17));
        cmd.setDevise(rs.getString(18));
        cmd.setCoursdev(rs.getBigDecimal(19));
        cmd.setPrixht_dev(rs.getBigDecimal(20));
        cmd.setTotht_dev(rs.getBigDecimal(21));
        cmd.setRem1(rs.getBigDecimal(22));
        cmd.setRem2(rs.getBigDecimal(23));
        cmd.setRem3(rs.getBigDecimal(24));
        cmd.setTotPrix_HT(rs.getBigDecimal(25));
        cmd.setEmplacement(rs.getString(26));
        cmd.setAttachement(rs.getString(27));
        cmd.setLot(rs.getString(28));
        cmd.setArreffour(rs.getString(29));
        cmd.setCclmarche(rs.getString(30));
        cmd.setCcldate(rs.getDate(31));
        cmd.setCclcolis(rs.getBigDecimal(32));
        cmd.setArqtecolis(rs.getInt(33));
        cmd.setCclpaht(rs.getBigDecimal(34));
        cmd.setNLOTDATEPER(rs.getString(35));
        cmd.setBPLNUM(rs.getInt(36));
        cmd.setBPLORDRE(rs.getInt(37));
        cmd.setBPLSEQ(rs.getInt(38));
        cmd.setBPLTYPE(rs.getInt(39));
        cmd.setBPLDOTATION(rs.getInt(40));
        cmd.setBPLSTADE(rs.getInt(41));
        cmd.setDepot(rs.getString(42));
        cmd.setBPLENT(rs.getString(43));
        cmd.setBPLBELIENCODE(rs.getString(44));
        cmd.setBPLBELIENNUM(rs.getInt(45));
        cmd.setBPLCODE(rs.getString(46));
        cmd.setBPLCL(rs.getString(47));
        cmd.setARPOIDS(rs.getInt(48));
        cmd.setSTDEPOT(rs.getString(49));
        cmd.setSTNUMARM1(rs.getString(50));
        cmd.setSTNUMARM2(rs.getString(51));
        cmd.setARNUMEROTE(rs.getInt(52));
        cmd.setARCOMP(rs.getInt(53));
        cmd.setEtat(rs.getBoolean(54));
        cmd.setSeqLib(rs.getInt(55));
        cmd.setComment_mag(rs.getString(56));
        cmd.setCclcolisage(rs.getString(57));
        cmd.setCclnbcolis(rs.getInt(58));
        cmd.setCclpack(rs.getInt(59));
        cmd.setRayon(rs.getString(60));
        cmd.setCclpromo(rs.getInt(61));

        return cmd;
    }
}
