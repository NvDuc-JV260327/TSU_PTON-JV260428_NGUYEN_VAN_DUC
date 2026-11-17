package com.example.tsu_ptonjv250101_nguyen_van_duc.Service;

import com.example.tsu_ptonjv250101_nguyen_van_duc.exception.DuplicateResourceException;
import com.example.tsu_ptonjv250101_nguyen_van_duc.exception.ResourceNotFoundException;
import com.example.tsu_ptonjv250101_nguyen_van_duc.exception.StatusChangeErrorException;
import com.example.tsu_ptonjv250101_nguyen_van_duc.model.dto.request.ProductDTO;
import com.example.tsu_ptonjv250101_nguyen_van_duc.model.entity.Product;
import com.example.tsu_ptonjv250101_nguyen_van_duc.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // hiển thị tất cả sp
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // thêm mới sản phẩm
    public Product addProduct(ProductDTO createProductDTO) {
        if(productRepository.existsByProductName(createProductDTO.getProductName())) {
            throw new DuplicateResourceException("Tên sản phẩm đã tồn tại");
        }
        Product product = Product.builder()
                .productName(createProductDTO.getProductName())
                .price(createProductDTO.getPrice())
                .currencyUnit(createProductDTO.getCurrencyUnit())
                .importDate(createProductDTO.getImportDate())
                .warrantyPeriod(createProductDTO.getWarrantyPeriod())
                .warrantyUnit(createProductDTO.getWarrantyUnit())
                .productImage(createProductDTO.getProductImage())
                .description(createProductDTO.getDescription())
                .status(createProductDTO.getStatus())
                .build();

        return productRepository.save(product);
    }

    // cập nhật sp theo mã sp
    public Product updateProduct(Long updateID, ProductDTO productDTO) {
        Product updateProduct = productRepository.findById(updateID).orElseThrow(
                () -> new ResourceNotFoundException("Id sản phẩm không tồn tại")
        );
        updateProduct.setProductName(productDTO.getProductName());
        updateProduct.setPrice(productDTO.getPrice());
        updateProduct.setCurrencyUnit(productDTO.getCurrencyUnit());
        updateProduct.setImportDate(productDTO.getImportDate());
        updateProduct.setWarrantyPeriod(productDTO.getWarrantyPeriod());
        updateProduct.setWarrantyUnit(productDTO.getWarrantyUnit());
        updateProduct.setProductImage(productDTO.getProductImage());
        updateProduct.setDescription(productDTO.getDescription());
        updateProduct.setStatus(productDTO.getStatus());

        return productRepository.save(updateProduct);
    }

    // thay đổi trạng thái sp(nếu đang là 2 thì k đc thay đổi)
    public String updateProductStatus(Long updateID, Byte status) {
        Product updateProduct = productRepository.findById(updateID).orElseThrow(
                () -> new ResourceNotFoundException("Id sản phẩm không tồn tại")
        );
        if(updateProduct.getStatus() == 2) {
            throw new StatusChangeErrorException("Trạng thái không thể thay đổi");
        }
        updateProduct.setStatus(status);
        productRepository.save(updateProduct);
        return "Cập nhật trạng thái thành công";
    }

    // tìm kiếm theo tên sp (tương đối)
    public List<Product> searchProductsByName(String searchString) {
        List<Product> listByName = productRepository.findByProductNameContainingIgnoreCase(searchString);
        if(listByName.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy sản phẩm nào");
        }
        return listByName;
    }

    // tìm kiếm theo status (chính xác)
    public List<Product> searchByStatus(Byte status) {
        List<Product> listByStatus = productRepository.findByStatus(status);
        if(listByStatus.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy sản phẩm nào");
        }
        return listByStatus;
    }
}
