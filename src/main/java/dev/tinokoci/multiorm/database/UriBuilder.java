package dev.tinokoci.multiorm.database;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UriBuilder {

    @Builder.Default
    private String host = "127.0.0.1";

    private Integer port;
    private String database;
    private String user;
    private String password;
}
