package com.synapse.ai.volvo.DTO;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 150, message = "Name must not exceed 150 characters")
    private String name;

    @NotBlank(message = "Roll is required")
    @Size(max = 50, message = "Roll must not exceed 50 characters")
    private String roll;

    @NotBlank(message = "Year is required")
    @Size(max = 20, message = "Year must not exceed 20 characters")
    private String year;

    @NotBlank(message = "Branch is required")
    @Size(max = 100, message = "Branch must not exceed 100 characters")
    private String branch;

    @NotBlank(message = "Section is required")
    @Size(max = 20, message = "Section must not exceed 20 characters")
    private String sec;

    @NotBlank(message = "Themes is required")
    @Size(max = 255, message = "Themes must not exceed 255 characters")
    private String themes;

    private String description;

    @Size(max = 1000, message = "Live URL must not exceed 1000 characters")
    private String liveUrl;

    @Size(max = 1000, message = "ZIP file URL must not exceed 1000 characters")
    private String zipFileUrl;

    private String email;

    private String phoneNumber;
}
