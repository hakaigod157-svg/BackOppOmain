package com.opham.prepa.partage;

import java.time.LocalDate;

import com.opham.prepa.Utils.UserSessionManager;
import com.opham.prepa.repository.Partage.CodeGenRepository;
import org.springframework.stereotype.Component;





@Component
public class CodeGenerator {



    private enum CodeType {
        DM("MAINT_OPHAM..MAINT_DEMANDE",     "DEMANDE_CODE"),
        MT("MAINT_OPHAM..MAINT_MAINTENANCE", "MAINT_CODE");

        final String table;
        final String colonne;

        CodeType(String table, String colonne) {
            this.table = table;
            this.colonne = colonne;
        }

        static CodeType fromLettres(String lettres) {
            try {
                return valueOf(lettres);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Type de code inconnu : " + lettres);
            }
        }
    }

    private static final int LONGUEUR_ITERATION = 4;
    private final UserSessionManager userSessionManager;

    private final CodeGenRepository repository;

    public CodeGenerator(UserSessionManager userSessionManager, CodeGenRepository repository) {
        this.userSessionManager = userSessionManager;
        this.repository = repository;
    }


 /**   public String generateCode(String lettres) {
        String prefix = buildPrefix(lettres);
        String dernier = repository.getLastCode(prefix);
        int next = extraireIteration(dernier, prefix) + 1;
        return prefix + formatIteration(next);
    }
*/
 public String generateCode(String sessionId, String lettres) {
     CodeType type = CodeType.fromLettres(lettres);

     String prefix = buildPrefix(lettres);
     String dernier = repository.getLastCode(sessionId, prefix, type.table, type.colonne);
     int next = extraireIteration(dernier, prefix) + 1;
     return prefix + formatIteration(next);
 }

    private String buildPrefix(String lettres) {
        LocalDate now = LocalDate.now();
        return lettres
                + String.format("%02d", now.getYear() % 100)
                + String.format("%02d", now.getMonthValue());
    }

    private String formatIteration(int n) {
        return String.format("%0" + LONGUEUR_ITERATION + "d", n);
    }

    private int extraireIteration(String code, String prefix) {
        if (code == null || !code.startsWith(prefix)) return 0;
        return Integer.parseInt(code.substring(prefix.length()));
    }
}