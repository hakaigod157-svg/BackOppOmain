package com.opham.prepa.repository.Catalogue;
import com.opham.prepa.model.Catalogue.*;
import java.util.List;


public interface CatalogueRepository {
    List<catalogue> findCatalogue(String sessionId, String clientcode, int moiscode, int anneecode, String tarifcode, int distribue);

  int findTestCatalogue(String sessionId, int moiscode, int anneecode);

    int genereCatalogue(String sessionId, int moiscode, int anneecode);

    List<listLabo> findListLabo(String sessionId);

    List<listCodeBarre> findListCodeBarre(String sessionId);

    int insertCodeBarre(String sessionId, String labo, String article);
}
