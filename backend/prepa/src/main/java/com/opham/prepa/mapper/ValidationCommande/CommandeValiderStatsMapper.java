package com.opham.prepa.mapper.ValidationCommande;


import com.opham.prepa.model.ValidationCommande.CommandeValiderStats;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandeValiderStatsMapper implements RowMapper<CommandeValiderStats>{

    @Override
    public CommandeValiderStats mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommandeValiderStats cmd = new CommandeValiderStats();
        cmd.setUser_valid(rs.getString(1));
        cmd.setNb(rs.getBigDecimal(2));
        cmd.setPercent(rs.getString(3));
        return cmd;
    }


}
