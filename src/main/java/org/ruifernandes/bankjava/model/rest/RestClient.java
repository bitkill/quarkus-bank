package org.ruifernandes.bankjava.model.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestClient {

    private Integer id;

    private String name;

    private String email;

    private String phone;

}
