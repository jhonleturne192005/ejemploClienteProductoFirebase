/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.firebasepractica.service;

import com.example.firebasepractica.models.Productos;
import com.example.firebasepractica.repository.RepositoryFirebase;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author USUARIO
 */
public class Sproductos 
{
    RepositoryFirebase rf;
    
    public Sproductos()
    {
        rf=new RepositoryFirebase();
    }
    
    
    public void insert(Map<String, Object> data)
    {
        rf.FirebaseInsertOrUpdate(Productos.coleccionProductos, data,null);
    }
    
    
    public void update(Map<String, Object> data,String iddocumento)
    {
        rf.FirebaseInsertOrUpdate(Productos.coleccionProductos, data,iddocumento);
    }
    
    
    public List<Productos> read()
    {
        List<QueryDocumentSnapshot> documents=rf.FirebaseRead(Productos.coleccionProductos);
        Productos p;
        List<Productos> lstproductos=new ArrayList<>();
        
        for(QueryDocumentSnapshot document : documents)
        {
            System.out.println(document.getId());
            p=document.toObject(Productos.class);
            p.setId(document.getId());
            /*p=new Productos();
            p.setNombre(document.getString(Productos.PnombreK));
            p.setEmpresa(document.getString(Productos.PempresaK));
            p.setPrecio_unitario(document.getDouble(Productos.Pprecio_unitarioK));
            p.setDescuento(document.getDouble(Productos.PdescuentoK));
            p.setProveedor(document.getString(Productos.PproveedorK));
            p.setDescripcion(document.getString(Productos.PdescripcionK));*/
            lstproductos.add(p);
        }
        return lstproductos;
    }
    
    
    
    public List<Productos> buscar(String campo,String valor) throws InterruptedException, ExecutionException
    {
        List<QueryDocumentSnapshot> documents =rf.buscar(Productos.coleccionProductos, campo,valor);
        Productos p;
        List<Productos> lstproduc=new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            System.out.println(document.getId());
            lstproduc.add(document.toObject(Productos.class));}
        return lstproduc;
    }
    
   
    public Integer eliminar(String id)
    {
        try
        {
           rf.eliminar(Productos.coleccionProductos,id);
           return 1;
        }
        catch(Exception ex)
        {
            
        }
        return 0;
        
    }
    
 
}
