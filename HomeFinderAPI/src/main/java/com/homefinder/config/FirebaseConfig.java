package com.homefinder.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value(value="classpath:serviceAccount.json")
    private Resource serviceAccountResource;

    @Bean
    public FirebaseApp initialize() throws IOException {
        InputStream serviceAccount = serviceAccountResource.getInputStream();
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://homefinder-7c018-default-rtdb.europe-west1.firebasedatabase.app")
                .build();

        return FirebaseApp.initializeApp(options);
        }

    @Bean
    @DependsOn(value="initialize")
    public FirebaseAuth firebaseAuth(){
        return FirebaseAuth.getInstance();
    }
    //    @Bean
//    public StorageClient storageClient(){
//        return StorageClient.getInstance();
//    }
    @Bean
    @DependsOn(value="initialize")
    public FirebaseDatabase firebaseDatabase(){
        return FirebaseDatabase.getInstance();
    }

}
