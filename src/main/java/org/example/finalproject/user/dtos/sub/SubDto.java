package org.example.finalproject.user.dtos.sub;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubDto {
    private Long id;
    private Long clientNr;
    private String lastName;
}
