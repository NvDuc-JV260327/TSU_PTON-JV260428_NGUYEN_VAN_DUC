package com.example.tsu_ptonjv250101_nguyen_van_duc.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String productName;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 1, message = "Giá sản phẩm phải lớn hơn 0")
    private double price;

    @NotBlank(message = "Đơn vị tiền tệ không được để trống")
    @Size(max = 10, message = "Đơn vị tiền tệ tối đa 10 ký tự")
    private String currencyUnit;

    @NotNull(message = "Ngày nhập hàng không được để trống")
    private LocalDate importDate;

    @NotNull(message = "Thời gian bảo hành không được để trống")
    private int warrantyPeriod;

    @NotBlank(message = "Đơn vị tính thời gian bảo hành không được để trống")
    private String warrantyUnit;

    private String productImage;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    private Byte status;
}
