package com.hillel.auto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by alpa on 5/21/20
 */
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Author {

    @JsonProperty("username")
    private String username;
    @JsonProperty("bio")
    private String bio;
    @JsonProperty("image")
    private String image;
    @JsonProperty("following")
    private Boolean following;
}
