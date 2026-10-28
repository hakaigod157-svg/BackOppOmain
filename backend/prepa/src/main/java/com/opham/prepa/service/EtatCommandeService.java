package com.opham.prepa.service;

import com.opham.prepa.Utils.Convert;
import com.opham.prepa.Utils.DataSourceConfig;
import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.EtatCommande.*;
import com.opham.prepa.model.EtatCommande.*;
import com.opham.prepa.repository.Etatcommande.EtatcommandeRepository;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Types;
import java.util.*;
@Repository
public class EtatCommandeService implements EtatcommandeRepository {

    private final UserSessionManager userSessionManager;

    @Autowired
    public EtatCommandeService(UserSessionManager userSessionManager) {

        this.userSessionManager = userSessionManager;
    }


    @Override
    public List<EtatCommande> findEtatcommande(String sessionId,String CodeCommande, String CodeClient, Date DateDeb, Date DateFin , String Axe) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        return jdbcTemplate.query("exec x_Etat_commande ?,?,?,?,?",
                new EtatCommandeMapper(),CodeCommande,CodeClient,DateDeb,DateFin,Axe);
    }
}
