package br.com.fiap.thermoscope.dtos.monitoredArea.request;

import br.com.fiap.thermoscope.models.enums.Level;
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
public class UpsertMonitoredAreaRequest {
    @NotBlank(message = "Name can't be blank")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @Size(max = 255, message = "Description can't exceed 255 characters")
    private String description;

    @NotNull(message = "Risk level can't be null")
    @ValidEnum(message = "Invalid option. Allowed values in risk field are LOW, MEDIUM or HIGH.", enumClass = Level.class)
    private String risk;
}
