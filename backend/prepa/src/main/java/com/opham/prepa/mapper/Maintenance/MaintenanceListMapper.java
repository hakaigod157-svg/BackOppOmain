package com.opham.prepa.mapper.Maintenance;


import com.opham.prepa.model.Maintenance.Maintenance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaintenanceListMapper implements RowMapper<Maintenance> {

    @Override
    public Maintenance mapRow(ResultSet rs, int rowNum) throws SQLException {
        Maintenance mtnce = new Maintenance();
        mtnce.setMAINT_CODE(rs.getString("MAINT_CODE"));
        mtnce.setMAINT_DEMANDE(rs.getString("MAINT_DEMANDE"));
        mtnce.setMAINT_CLASSIFICATION(rs.getString("MAINT_CLASSIFICATION"));
        mtnce.setMAINT_TYPE(rs.getString("MAINT_TYPE"));
        mtnce.setMAINT_KM(rs.getString("MAINT_KM"));
        mtnce.setMAINT_USERMODIF(rs.getInt("MAINT_USERMODIF"));
        mtnce.setMAINT_USERCRE(rs.getInt("MAINT_USERCRE"));

        mtnce.setMAINT_DATE(rs.getDate("MAINT_DATE"));
        mtnce.setMAINT_DATECRE(rs.getDate("MAINT_DATECRE"));
        mtnce.setMAINT_DATEMODIF(rs.getDate("MAINT_DATEMODIF"));



        return mtnce;
    }
}