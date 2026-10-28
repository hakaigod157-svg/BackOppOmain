package com.opham.prepa.repository.Capa;

import com.opham.prepa.model.Capa.AtteVal;
import com.opham.prepa.model.Capa.Capa;
import com.opham.prepa.model.Capa.CauseRac;
import com.opham.prepa.model.Capa.ktable;
import com.opham.prepa.model.Utils.Action;

import java.util.Date;
import java.util.List;

public interface CapaRepository {
    List<Capa> listCapa(String sessionId,String code, String description, String emetteur, String libelleDep, String type, Date date, Integer causevalid, Integer causeatte, Integer attevalid, Integer attemiseappli, Integer appli);
    void updateFNCPS(String sessionId,int ncpsSeq,String ncpsCode,Date ncpsDate, String ncpsDescription, String ncpsDepartement, String ncpsType, String ncpsModeDetect, String ncpsProcessusDetect);
    void insertFNCPS(String sessionId,String ncpsEmetteur, String ncpsDescription , String ncpsDepartement, Date ncpsDate, int ncpsEtat, int ncpsValide);
    List<AtteVal> listatteval(String sessionId);
    List<CauseRac> findByCodeAction(String sessionId,int actionCauseseq);
    void deleteFACTION(String sessionId,int actionSeq);
    void updateFACTION(String sessionId,int actionSeq,String actionResponsable,Date actionDateprev);
    void updateFACTION2(String sessionId,int actionSeq,Date actionDateanaleffic,Date actionDaterelleeffic);
    void updateFACTION3(String sessionId,int actionSeq,Date actionDaterelleeffic,String actionCommentaire);
    List<ktable> findktable(String sessionId);
    void updateKTABLE(String sessionId,int seqk);
    void insertKTABLE(String sessionId);
}
