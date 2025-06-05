package br.com.fiap.thermoscope.dtos.team.request;

import br.com.fiap.thermoscope.models.enums.TeamStatus;
import br.com.fiap.thermoscope.validations.enums.ValidEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertTeamRequest {
    @NotBlank(message = "Name can't be blank")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Location can't be blank")
    @Size(min = 3, max = 100, message = "Location must be between 3 and 100 characters")
    private String location;

    @NotNull(message = "Status can't be null")
    @ValidEnum(message = "Invalid status. Allowed values in the status field are AVAILABLE, ON_MISSION or INACTIVE.", enumClass = TeamStatus.class)
    private String status;
}
