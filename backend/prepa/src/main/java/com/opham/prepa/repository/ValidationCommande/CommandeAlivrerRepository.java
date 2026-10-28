package com.opham.prepa.repository.ValidationCommande;

import com.opham.prepa.model.ValidationCommande.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CommandeAlivrerRepository {

    List<CommandeAlivrer> findCommandeAlivrer(String sessionId);

    int updateFCC2(String sessionId, String cc,int etat) ;

    int updateCOM_Client (String sessionId, String cc,String commentaires);

    List<CommandeAValider> findCommandeAValider(String id, String sessionId);

    List<ClientSolde> findClientSolde(String sessionId, String codeclient, int type);

    List<CommandeEncours> findCommandeEncours(String sessionId, String codeclient, int type);

    List<Avance> findAvance(String sessionId, String codeclient, int type);

    List<TestApresValide> onValideAdv(String sessionId, String cmd, String accord, String comAccord, String commClt, String comMag, String modeNorm, String modeEncours, BigDecimal impaye, BigDecimal retard,int bal,String ip);

    String onCreeTraiteAdv(String sessionId, String cmd,String traite,int isNew);

    List<Reglement> findReglement(String sessionId, String codeclient, int type);

    List<Commande> findCommande(String sessionId, String codeclient, int type);
    List<InfoApresBlocage> onBlockAdv(String sessionId,String cmd,String ip,int couleur);

    /*List<CommandeValider> findCommandeValider(String sessionId, Date datedeb, Date datefin, String codeCC, String client);*/

    int getStade_cmd(String sessionId,String code);

    int getSatde_bal(String sessionId,String code);

    Etape getEtapeAdv(String sessionId);

    List<ReglementBon> findReglementBon(String sessionId, String codeclient, int type);

    List<Traite> findTraite(String sessionId);

    List<List<Object>> findCommandeValider(String sessionId, Date datedeb, Date datefin, String codeCC, String client);
}
