package com.opham.prepa.service;

import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.mapper.Maintenance.*;
import com.opham.prepa.model.Maintenance.*;
import com.opham.prepa.partage.CodeGenerator;
import com.opham.prepa.repository.Maintenance.MaintenanceRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MaintenanceService implements MaintenanceRepository {

    private final UserSessionManager userSessionManager;
    private final CodeGenerator code;
    private final JdbcTemplate jdbcTemplate;

    public MaintenanceService(
            UserSessionManager userSessionManager,
            CodeGenerator code,
            JdbcTemplate jdbcTemplate) {
        this.userSessionManager = userSessionManager;
        this.code = code;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<DemandeMaint> getDemandeMaintenance(String sessionId) {
          //  String codeDemande
        JdbcTemplate jdbcTemplate =
                userSessionManager.getUserJdbcTemplate(sessionId);

        String sql ="call MAINT_OPHAM..x_ListeDemandes} ";

        System.out.println(sql);
        return jdbcTemplate.query(
                sql,
               // new Object[]{codeDemande},
                new DemandeMaintenanceMapper()
        );
    }



    @Override
    public List<Maintenance> getMaintenance(
            String sessionId
    ) {

        JdbcTemplate jdbcTemplate =
                userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "{call MAINT_OPHAM..x_ListeMaintenances}";

        return jdbcTemplate.query(
                sql,

                new MaintenanceListMapper()
        );
    }



    @Override
    public List<DemandeMaint> searchDemandes(
            String sessionId,
            String search,
            String marque
    ) {
        JdbcTemplate jdbcTemplate =
                userSessionManager.getUserJdbcTemplate(sessionId);
        StringBuilder sql = new StringBuilder("""
            SELECT
                DEMANDE_CODE,
                DEMANDE_DATE,
                DEMANDE_VOITURE,
                DEMANDE_MARQUE,
                DEMANDE_TYPE,
                DEMANDE_AUTEUR,
                DEMANDE_DESCR,
                DEMANDE_ETAT,
                DEMANDE_DATE_CRE,
                DEMANDE_USER_CRE,
                DEMANDE_DATE_MODIF,
                DEMANDE_USER_MODIF
            FROM MAINT_OPHAM..MAINT_DEMANDE
            WHERE 1 = 1
            """);
        List<Object> params = new ArrayList<>();

        if (search != null && !search.trim().isEmpty()) {

            String value = "%" + search.trim() + "%";

            sql.append("""
                AND (
                       DEMANDE_CODE LIKE ?
                    OR DEMANDE_AUTEUR LIKE ?
                    OR DEMANDE_VOITURE LIKE ?
                    OR DEMANDE_MARQUE LIKE ?
                    OR DEMANDE_TYPE LIKE ?
                    OR DEMANDE_DESCR LIKE ?
                )
                """);
            params.add(value);
            params.add(value);
            params.add(value);
            params.add(value);
            params.add(value);
            params.add(value);
        }
        if (marque != null && !marque.trim().isEmpty()) {
            sql.append("""
                AND DEMANDE_MARQUE = ?
                """);
            params.add(marque.trim());
        }
        sql.append("""
            ORDER BY DEMANDE_DATE_CRE DESC, DEMANDE_CODE DESC
            """);
        return jdbcTemplate.query(
                sql.toString(),
                params.toArray(),
                new DemandeMaintenanceMapper()
        );
    }

    @Override
    public String creerDemandeCode(String sessionId) {
        return code.generateCode(sessionId,"DM");
    }

    @Override
    public String creeMtnceCode(String sessionId) {
        return code.generateCode(sessionId, "MT");
    }
    /**
    @Override
    public void validerDemande(String sessionId, String demandeCode) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String maintCode = creeMtnceCode(sessionId);
        String sql = "{call MAINT_OPHAM..x_ValiderDemande (?, ?)}";
        jdbcTemplate.update(sql, demandeCode, maintCode);
    }
    */







    @Override
    public void validerDemande(
            String sessionId,
            String demandeCode,
            Integer voitureKm,
            String classification
    ) {
        JdbcTemplate jdbcTemplate =
                userSessionManager.getUserJdbcTemplate(sessionId);

        String maintCode = creeMtnceCode(sessionId);

        String sql =
                "{call MAINT_OPHAM..x_ValiderDemande (?, ?, ?, ?)}";

        jdbcTemplate.update(
                sql,
                demandeCode,
                maintCode,
                voitureKm,
                classification
        );
    }



    @Override
    public void enregistrDmd(String sessionId, String demandeCode, String demandeVoiture, String demandeMarque, String demandeType, String demandeDescr) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_CreateDemande (?,?,?,?,?)}";
        jdbcTemplate.update(sql, demandeCode, demandeVoiture,demandeMarque,demandeType,demandeDescr);
    }

    @Override
    public void updateDemande(String sessionId, String demandeCode, Date demandeDate, String demandeVoiture, String demandeMarque, String demandeType, String demandeAuteur, String demandeDescr) {
        JdbcTemplate jdbcTemplate =
                userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "{call MAINT_OPHAM..x_Update_Edit (?, ?, ?, ?, ?, ?, ?)}";

        jdbcTemplate.update(
                sql,
                demandeCode,
                demandeDate,
                demandeVoiture,
                demandeMarque,
                demandeType,
                demandeAuteur,
                demandeDescr
        );
    }

    @Override
    public void refuserDemande(String sessionId, String demandeCode) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_UpdateDemande (3, ?)}";
        jdbcTemplate.update(sql, demandeCode);
    }

    @Override
    public void supprDmd(String sessionId, String demandeCode) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_DeleteDemande (?)}";
        jdbcTemplate.update(sql, demandeCode);
    }


    @Override
    public DemandeMaint getDmdbyCode(String sessionId, String demandeCode) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "{call MAINT_OPHAM..x_GetDemandeByCode(?)}";

        List<DemandeMaint> resultat = jdbcTemplate.query(
                sql,new Object[] { demandeCode },new DemandeMaintenanceMapper()
        );
        return resultat.isEmpty() ? null : resultat.get(0);
    }


    @Override
    public List<Voiture> getVoiture(String sessionId) {

        JdbcTemplate jdbcTemplate =
                userSessionManager.getUserJdbcTemplate(sessionId);

        String sql = "{call MAINT_OPHAM..x_ListeVoiture}";

        return jdbcTemplate.query(sql,new VoitureMapper()
        );
    }

@Override
    public Voiture getVoitureByNum(String sessionId, String numVoiture){
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_GetVoitureByNum(?)}";
        List<Voiture> resultat = jdbcTemplate.query(
                sql,new Object[] { numVoiture },new VoitureMapper()
        );
        return resultat.isEmpty() ? null : resultat.get(0);
    }

    @Override
    public Integer getVoitureLastKm(String sessionId, String numVoiture) {
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_GetVoitureLastKm(?)}";

        return jdbcTemplate.queryForObject(sql, Integer.class, numVoiture);
    }

    @Override
    public List<Enumerat>getEnum(String sessionId, String enumValeur) {

        JdbcTemplate jdbcTemplate =
                userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_GetEnumeration(?)}";
        return jdbcTemplate.query(sql,new Object[] { enumValeur },new EnumeratMapper());
    }


    @Override
    public List<Enumerat>getTypeMaintenance(String sessionId ) {

        JdbcTemplate jdbcTemplate =
                userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_ListeTypeMaint()}";
        return jdbcTemplate.query(sql,new EnumeratMapper());
    }
    @Override
    public List<Enumerat>getTypeClassific(String sessionId ) {

        JdbcTemplate jdbcTemplate =
                userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_ListeClassification()}";
        return jdbcTemplate.query(sql,new EnumeratMapper());
    }

    @Override
    public List<Etape>getEtapesParClass(String sessionId, String natureEtape, String classification) {

        JdbcTemplate jdbcTemplate =
                userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_ListeEtapeByClassification(?,?)}";

        return jdbcTemplate.query(sql,new Object[] {natureEtape, classification} ,new EtapeMapper());
    }

    @Override
    public void validerEtape(String sessionId, String demandeCode){
        JdbcTemplate jdbcTemplate =
                userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_GetNextEtape(?)}";
        jdbcTemplate.update(sql,demandeCode);
    }

    @Override
    public void fermerDemande(String sessionId, String demandeCode){
        JdbcTemplate jdbcTemplate = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_CloturerDemande(?)}";
        jdbcTemplate.update(sql, demandeCode);
    }

   /** @Override
    public String getLastEtape(String sessionId, String demandeCode){
        JdbcTemplate jdb = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql="{call MAINT_OPHAM..x_GetDemandeEtat(?)}";
        return jdb.queryForObject(sql, String.class, demandeCode);
    }
    */

    @Override
    public List<DemandeStatut> getDemandeStatus(String sessionId, String demandeCode){
        JdbcTemplate jdb = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql="{call MAINT_OPHAM..x_GetDemandeEtat(?)}";
        return jdb.query(sql, new Object[]{ demandeCode }, new DemandeStatutMapper());
    }

    @Override
    public List<Historique> getHistoriqueDmd(String sessionId, String demandeCode) {
        JdbcTemplate jdb = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_GetDemandeHistorique(?)}";

        return jdb.query(sql,  new Object[]{ demandeCode},new HistoriqueMapper());
    }


    /**
     * @param sessionId
     * @param maintCode
     * @return
     */
    @Override
    public List<EtapeLigne> getEtapePrepa(String sessionId , String maintCode){
        JdbcTemplate jdb = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_GetEtapeMaintP(?)}";
        return jdb.query(sql, new Object[] {maintCode},new EtapeLigneMapper());

    }

    @Override
    public List<Realisation> getEtapeReal(String sessionId, String maintCode){
        JdbcTemplate jdb = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_GetEtapeMaintR(?)}";
        return jdb.query(sql, new Object[] {maintCode},new RealisationMapper());
    }
////////////////////////////////


    /** @Override
    public void validerEtapePrepa(String sessionId, String maintCode, String etapeLigne, String intervenant) {
        JdbcTemplate jdb = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_ValiderEtapePrepa(?, ?, ?)}";
        jdb.update(sql, maintCode, etapeLigne, intervenant);
    }
    */
    @Override
    public void validerEtapePrepa(String sessionId, String maintCode, String etapeLigne) {
        JdbcTemplate jdb = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_ValiderEtapePrepa(?, ?)}";
        jdb.update(sql, maintCode, etapeLigne);
    }

    @Override
    public void validerEtapeReal(String sessionId, String maintCode, String intervReal,
                                 String action, String fnr, Double prix, String marque, String dateReal) {
        JdbcTemplate jdb = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_ValiderEtapeReal(?, ?, ?, ?, ?, ?, ?)}";
        jdb.update(sql, maintCode, intervReal, action, fnr, prix, marque, dateReal);
    }

    @Override
    public void addEtapeReal(String sessionId, String maintCode, String intervReal,
                             String datePrevu, String action, String fnr, Double prix, String marque) {
        JdbcTemplate jdb = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_CreateEtapeReal(?, ?, ?, ?, ?, ?, ?)}";
        jdb.update(sql, maintCode, intervReal, datePrevu, action, fnr, prix, marque);
    }


    @Override
    public List<Enumerat> getTypeAction(String sessionId){
        JdbcTemplate jdb = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_ListeTypeAction()}";
        return jdb.query(sql,new EnumeratMapper());

    }

    @Override
    public List<Enumerat> getFournisseurs(String sessionId){
        JdbcTemplate jdb = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_ListeFournisseurs()}";
        return jdb.query(sql,new EnumeratMapper());
    }
    @Override
    public List<Enumerat> getMarquePiece(String sessionId) {
        JdbcTemplate jdb = userSessionManager.getUserJdbcTemplate(sessionId);
        String sql = "{call MAINT_OPHAM..x_ListeMarquePiece()}";
        return jdb.query(sql,new EnumeratMapper());
    }
/**
 * 
 */



    }