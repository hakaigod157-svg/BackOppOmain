package com.opham.prepa.repository.BusinessIntelligence;

import com.opham.prepa.model.BusinessIntelligence.list_ca_annuel;
import com.opham.prepa.model.BusinessIntelligence.list_palier;
import com.opham.prepa.model.BusinessIntelligence.list_view;
import com.opham.prepa.model.BusinessIntelligence.taux_palier;

import java.math.BigDecimal;
import java.util.List;

public interface BusinessIntelligenceRepository {
    List<list_view> listView(String sessionId);

    List<list_ca_annuel> listCaAnnuel(String sessionId, String codeArticle);

    /*List<list_palier> listPalier(String sessionId, Double taux); */

    List<List<Object>>listPalier(String sessionId, Double taux , Double primeMax);

    List<taux_palier> list_taux(String sessionId);
}
