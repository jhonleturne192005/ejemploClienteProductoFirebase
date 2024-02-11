package com.example.firebasepractica;

import com.example.firebasepractica.repository.RepositoryFirebase;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirebasepracticaApplication {

    public static Boolean registrado = true;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(FirebasepracticaApplication.class, args);
        if (FirebaseApp.getApps().size() <= 0) {
            initFirebase();
        }
    }

    public static void initFirebase() throws IOException {
        InputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\autenticacion.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RepositoryFirebase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serviceAccount.close();
            } catch (IOException ex) {
                Logger.getLogger(RepositoryFirebase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
