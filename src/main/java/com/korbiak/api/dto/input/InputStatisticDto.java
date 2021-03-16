package com.korbiak.api.dto.input;

import com.korbiak.api.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputStatisticDto {

    private DepartmentDto departmentDto;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;
}
