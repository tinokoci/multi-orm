package com.example.app;

import dev.tinokoci.multiorm.database.DatabaseService;
import dev.tinokoci.multiorm.database.PostgreSQLDatabaseService;
import dev.tinokoci.multiorm.database.UriBuilder;

public class Test {

    public static void main(String[] args) {
        DatabaseService databaseService = new PostgreSQLDatabaseService().connect(
                UriBuilder.builder()
                        .host("localhost")
                        .port(5432)
                        .build()
        );
    }
}
