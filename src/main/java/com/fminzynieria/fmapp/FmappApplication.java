package com.fminzynieria.fmapp;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class FmappApplication {

    private static final String ACCESS_TOKEN = "mwgaGAY8cwAAAAAAAAAACQPOdlXDciI8DHVyrG5L684Ej3b1Nx8-k5wW9FnU0NF7";

    public static void main(String[] args) throws DbxException, Exception {
        SpringApplication.run(FmappApplication.class, args);
        showConnection(dbxClientV2());
    }

    //Dropbox client
    @Bean
    public static DbxClientV2 dbxClientV2() throws Exception {
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        return client;
    }

    //Test DBX account connection
    public static void showConnection(DbxClientV2 client) throws Exception {
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
    }
}

