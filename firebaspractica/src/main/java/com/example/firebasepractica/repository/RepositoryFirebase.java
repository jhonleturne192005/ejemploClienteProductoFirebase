/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.firebasepractica.repository;

import com.example.firebasepractica.Messages;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hibernate.Hibernate.collection;
import static org.springframework.core.convert.TypeDescriptor.collection;

/**
 *
 * @author USUARIO
 */
public class RepositoryFirebase 
{
    
    private static String errorConeccion="Error: No se a podido establecer coneccion con firebase";
    
    public void FirebaseInsertOrUpdate(String coleccion, Map<String, Object> data,String documento)
    {
        try
        {
            Firestore fs = FirestoreClient.getFirestore();
            if(fs!=null)
            {
                DocumentReference docRef = null;
                if(documento==null)
                    docRef=fs.collection(coleccion).document();
                else
                    docRef=fs.collection(coleccion).document(documento);
                ApiFuture<WriteResult> result = docRef.set(data);
                System.out.println("Update time : " + result.get().getUpdateTime());
                return;
            }
            System.out.println(this.errorConeccion);
        }
        catch(Exception ex)
        {
            System.out.println("Error: "+ex.getMessage());
        }
    }
    
    public List<QueryDocumentSnapshot> FirebaseRead(String coleccion)
    {
        try
        {
            Firestore fs = FirestoreClient.getFirestore();
            if(fs!=null)
            {
                ApiFuture<QuerySnapshot> query = fs.collection(coleccion).get();
                QuerySnapshot querySnapshot = query.get();
                List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
                return documents;
            }
            System.out.println(this.errorConeccion);
        }
        catch(Exception ex)
        {
            System.out.println("Error: "+ex.getMessage());
        }
        return null;
    }
    

    public List<QueryDocumentSnapshot> buscar(String coleccion,String campo,String valor)
    {
        try {
            Firestore fs = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> qs = fs.collection(coleccion).whereEqualTo(campo, valor).get();
            List<QueryDocumentSnapshot> documents = qs.get().getDocuments();
            System.out.println("dsddddddd");
            return documents;
        } catch (InterruptedException ex) {
            Logger.getLogger(RepositoryFirebase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(RepositoryFirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
  
    
    public void eliminar(String coleccion,String id)
    {
        try
        {
            Firestore fs = FirestoreClient.getFirestore();
            DocumentReference docRef = fs.collection(coleccion).document(id);
            docRef.delete();
            System.out.println("Documento eliminado");   
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            Logger.getLogger(RepositoryFirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
  
}
