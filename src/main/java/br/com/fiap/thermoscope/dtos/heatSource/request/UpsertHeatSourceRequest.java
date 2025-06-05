package br.com.fiap.thermoscope.dtos.heatSource.request;

import br.com.fiap.thermoscope.models.enums.ConfirmedHeatSource;
import br.com.fiap.thermoscope.models.enums.Level;
import br.com.fiap.thermoscope.validations.enums.ValidEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertHeatSourceRequest {
    @NotNull(message = "Area ID can't be null")
    private UUID areaId;

    @NotNull(message = "Intensity level can't be null")
    @ValidEnum(message = "Invalid option. Allowed values in intensity field are LOW, MEDIUM or HIGH.", enumClass = Level.class)
    private String intensity;

    @NotNull(message = "Confirmed status can't be null")
    @ValidEnum(message = "Invalid option. Allowed values in confirmed field are Y or N.", enumClass = ConfirmedHeatSource.class)
    private String confirmed;
}
