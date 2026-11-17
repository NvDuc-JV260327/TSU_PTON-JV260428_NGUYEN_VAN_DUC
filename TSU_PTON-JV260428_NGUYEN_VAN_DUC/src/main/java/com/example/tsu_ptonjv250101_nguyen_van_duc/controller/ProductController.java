package com.example.tsu_ptonjv250101_nguyen_van_duc.controller;

import com.example.tsu_ptonjv250101_nguyen_van_duc.Service.ProductService;
import com.example.tsu_ptonjv250101_nguyen_van_duc.model.dto.request.ProductDTO;
import com.example.tsu_ptonjv250101_nguyen_van_duc.model.dto.request.StatusRequest;
import com.example.tsu_ptonjv250101_nguyen_van_duc.model.dto.response.ApiResponse;
import com.example.tsu_ptonjv250101_nguyen_van_duc.model.entity.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    // lấy tất cả sp
    @GetMapping
    public ApiResponse<List<Product>> getAllProducts() {
        return ApiResponse.<List<Product>>builder()
                .status(200)
                .message("Lấy danh sách sản phẩm thành công")
                .data(productService.getAllProducts())
                .timestamp(LocalDateTime.now())
                .build();
    }

    // thêm mới sp
    @PostMapping
    public ApiResponse<Product> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ApiResponse.<Product>builder()
                .status(204)
                .message("Thêm mới sản phẩm thành công")
                .data(productService.addProduct(productDTO))
                .timestamp(LocalDateTime.now())
                .build();
    }

    // update sp
    @PutMapping("/{id}")
    public ApiResponse<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        return ApiResponse.<Product>builder()
                .status(200)
                .message("Cập nhật thông tin sản phẩm thành công")
                .data(productService.updateProduct(id, productDTO))
                .timestamp(LocalDateTime.now())
                .build();
    }

    // cập nhật status sp
    @PutMapping("/update-status/{id}")
    public ApiResponse<String> updateStatusProduct(@PathVariable Long id, @RequestBody StatusRequest statusRequest) {
        return ApiResponse.<String>builder()
                .status(200)
                .message("Cập nhật trạng thái sản phẩm thành công")
                .data(productService.updateProductStatus(id, statusRequest.getStatus()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    // tìm kiếm theo tên(tương đối)
    @GetMapping("/search-name")
    public ApiResponse<?> searchProductsByName(@RequestParam String name) {
        List<Product> listByProductName = productService.searchProductsByName(name);
        return ApiResponse.<List<Product>>builder()
                .status(200)
                .message("Tìm kiếm thành công")
                .data(listByProductName)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // tìm kiếm theo status (chính xác)
    @GetMapping("/search-status")
    public ApiResponse<?> searchProductsByStatus(@RequestParam Byte status) {
        List<Product> listByStatus = productService.searchByStatus(status);
        return ApiResponse.<List<Product>>builder()
                .status(200)
                .message("Tìm kiếm thành công")
                .data(listByStatus)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
