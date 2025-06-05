package br.com.fiap.thermoscope.dtos.incident.request;

import br.com.fiap.thermoscope.models.enums.IncidentStatus;
import br.com.fiap.thermoscope.validations.conclusionDate.ValidConclusionDate;
import br.com.fiap.thermoscope.validations.enums.ValidEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertIncidentRequest {
    @NotNull(message = "Heat source ID can't be null")
    private UUID heatSourceId;

    @NotNull(message = "Team ID can't be null")
    private UUID teamId;

    @NotNull(message = "User ID can't be null")
    private UUID userId;

    @NotNull(message = "Status can't be null")
    @ValidEnum(message = "Invalid option. Allowed values in status field are PENDING, IN_PROGRESS, COMPLETED or CANCELLED.", enumClass = IncidentStatus.class)
    private String status;

    @Size(max = 500, message = "Report can't exceed 500 characters")
    private String report;

    @ValidConclusionDate
    private LocalDate conclusionDate;
}
