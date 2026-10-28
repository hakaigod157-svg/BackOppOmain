package com.opham.prepa.repository.AppelEntrant;

import com.opham.prepa.model.AppelEntrant.ActionFirstAppel;
import com.opham.prepa.model.AppelEntrant.AppelEntrant;
import com.opham.prepa.model.AppelEntrant.AppelSortant;
import com.opham.prepa.model.AppelEntrant.MoyenneAppel;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface CallRepository {
    List<AppelEntrant> findAppelEntrant(String sessionId, Date date_deb, Date date_fin);
    List<AppelSortant> findAppelSortant(String sessionId, Date dateSortant, String datePart, Time timePart, String numero);
    List<ActionFirstAppel> findActionFirstAppel(String sessionId, BigDecimal seqEntrantBig);

    void updateEtat(String sessionId, BigDecimal AppelIdBig);
    //void updateEtat1(Long AppelId);

    int updateEtatSortie(String sessionId, BigDecimal seqEtatBig, int etat, BigDecimal seqSortie, int rappel, String src_sortant);

    int findxTestMaj(String sessionId, BigDecimal seq);


    int insertFrappel(String sessionId, BigDecimal seqEntrant, BigDecimal seqSortant, String user, String ip_local);

    List<MoyenneAppel> findAppelMoyenne(String sessionId, Date date_deb, Date date_fin);
}
