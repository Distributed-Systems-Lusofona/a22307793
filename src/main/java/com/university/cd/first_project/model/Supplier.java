package com.university.cd.first_project.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    private Long id;

    @NotBlank(message = "Company name is required")
    @Size(max = 100)
    private String companyName;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    // Extra fields
    @NotBlank(message = "Contact person is required")
    private String contactPerson;

    @Size(max = 15)
    private String phoneNumber;
}
