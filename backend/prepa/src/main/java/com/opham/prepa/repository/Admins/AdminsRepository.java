package com.opham.prepa.repository.Admins;

import com.opham.prepa.model.Admins.Acces;
import com.opham.prepa.model.Admins.Groupe;
import com.opham.prepa.model.Admins.Users;

import java.util.List;

public interface AdminsRepository {
    List<Groupe> listGroupe(String sessionId);
    List<Users> listUsers(String sessionId,String groupe);

    List<Users> listMembres(String sessionId,String groupe);

    List<Acces> listAcces(String sessionId,String groupe);

    List<Acces> listDroit(String sessionId,String groupe);

    int insert_Membres(String sessionId,String groupe,String users);

    int insert_droit(String sessionId,Acces A);

    int delete_Membres(String sessionId,String groupe,String users);

    int delete_droit(String sessionId,String groupe,String droit);

    int update_droit(String sessionId,String groupe,String droit,boolean w,boolean r);

    int insert_groupe(String sessionId,Groupe G);


}
