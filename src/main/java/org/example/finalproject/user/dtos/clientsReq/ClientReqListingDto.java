package org.example.finalproject.user.dtos.clientsReq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finalproject.user.entities.enums.Cities;

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

}
