package org.example.finalproject.user.dtos.clients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.StatusEnum;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientReqListingDto {
    private Long id;

    private Long ticketNr;

    private String firstName;

    private String lastName;

    private Cities city;

    private String address;

    private StatusEnum status;

}
