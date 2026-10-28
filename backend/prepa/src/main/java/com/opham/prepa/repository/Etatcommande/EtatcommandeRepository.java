package com.opham.prepa.repository.Etatcommande;

import com.opham.prepa.model.EtatCommande.EtatCommande;

import java.util.Date;
import java.util.List;

public interface EtatcommandeRepository {
    List<EtatCommande> findEtatcommande(String sessionId, String CodeCommande, String CodeClient, Date DateDeb, Date DateFin,String Axe);


}
