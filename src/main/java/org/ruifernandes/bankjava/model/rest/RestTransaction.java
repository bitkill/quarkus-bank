package org.ruifernandes.bankjava.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestTransaction {

    private Integer id;

    @JsonProperty("from_client_id")
    private Integer fromClientId;

    @JsonProperty("to_client_id")
    private Integer toClientId;

    private Integer amount;

}
