/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.firebasepractica.service;

import com.example.firebasepractica.models.Productos;
import com.example.firebasepractica.models.Usuario;
import com.example.firebasepractica.repository.RepositoryFirebase;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class Susuario 
{
    RepositoryFirebase rf;
    
    public Susuario()
    {
        rf=new RepositoryFirebase();
    }
    
    public int insert(Map<String, Object> data)
    {
        try {
            Object valor=data.get(Usuario.PusuarioK);
            if(buscar(valor.toString()).size()<=0){
                rf.FirebaseInsertOrUpdate(Usuario.coleccionUsuario, data,null);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Susuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Susuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<Usuario> buscar(String valor) throws InterruptedException, ExecutionException
    {
        List<QueryDocumentSnapshot> documents =rf.buscar(Usuario.coleccionUsuario, Usuario.PusuarioK,valor);
        Usuario u;
        List<Usuario> lstusuario=new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            u=document.toObject(Usuario.class);
            lstusuario.add(u);
        }
        return lstusuario;
    } 
}
