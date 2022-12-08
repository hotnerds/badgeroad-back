package com.hotnerds.badgeroad.yamlreadDB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DBSource {
    private String driver;
    private String url;
    private String username;
    private String password;
}
