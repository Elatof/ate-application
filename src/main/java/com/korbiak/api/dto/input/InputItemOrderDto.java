package com.korbiak.api.dto.input;

import com.korbiak.api.dto.CustomerDto;
import com.korbiak.api.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputItemOrderDto {

    @NotNull
    private Date endDate;

    @NotNull
    private ItemDto item;

    @NotNull
    private CustomerDto customer;
}
