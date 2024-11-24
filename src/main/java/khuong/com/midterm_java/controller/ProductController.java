package khuong.com.midterm_java.controller;


import khuong.com.midterm_java.dto.ProductDTO;
import khuong.com.midterm_java.dto.ResponseDTO;
import khuong.com.midterm_java.entity.Category;
import khuong.com.midterm_java.entity.Product;
import khuong.com.midterm_java.repository.CategoryRepository;
import khuong.com.midterm_java.repository.ProductRepository;
import khuong.com.midterm_java.service.ImageUploadService;
import khuong.com.midterm_java.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = Logger.getLogger(ProductService.class.getName());
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public ResponseDTO<List<ProductDTO>> getProducts() {
        ResponseDTO<List<ProductDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(productService.getAll());
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @PutMapping("{id}")
    public ResponseDTO<Void> update(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        productService.update(id, productDTO);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thành công sản phẩm")
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseDTO<Void> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Xóa thành công sản phẩm")
                .build();
    }

    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping("/{productID}/updateImage")
    public String updateImageProduct(@PathVariable("productID") Long productID, @RequestParam("file") MultipartFile file) throws IOException {
        String img_url =imageUploadService.uploadImage(file);
        Product product = productRepository.findById(productID).orElseThrow(RuntimeException::new);
        product.setImageProduct(img_url);
        productRepository.save(product);

        return "Product img updated successfully";
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseDTO<Void>> createOrUpdateProduct(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam("name") String name,
            @RequestParam(value = "category_id", required = false) Long categoryId,
            @RequestParam("price") double price,
            @RequestParam("weight") double weight,
            @RequestParam("brand") String brand,
            @RequestParam("color") String color,
            @RequestParam("description") String description,
            @RequestParam(value = "imageProduct", required = false) MultipartFile imageProduct) throws IOException {

        // Kiểm tra nếu `category_id` bị thiếu
        if (categoryId == null) {
            return ResponseEntity.badRequest().body(
                    ResponseDTO.<Void>builder()
                            .status(400)
                            .message("Missing required parameter: category_id")
                            .build()
            );
        }

        String img_url = null;
        if (imageProduct != null && !imageProduct.isEmpty()) {
            img_url = imageUploadService.uploadImage(imageProduct);
        }

        Product product;

        if (id != null) {
            // Nếu `id` được truyền, tiến hành cập nhật sản phẩm
            product = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        } else {
            // Nếu `id` không được truyền, tạo sản phẩm mới
            product = new Product();
        }

        // Set các thuộc tính sản phẩm
        product.setName(name);
        product.setPrice(price);
        product.setWeight(weight);
        product.setBrand(brand);
        product.setColor(color);
        product.setDescription(description);

        // Tìm danh mục từ `category_id` và liên kết với sản phẩm
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
        product.setCategory(category);

        // Cập nhật ảnh sản phẩm nếu được upload
        if (imageProduct != null && !imageProduct.isEmpty()) {
            product.setImageProduct(img_url);
        }

        productRepository.save(product);

        ResponseDTO<Void> dto = new ResponseDTO<>();
        dto.setStatus(200);
        dto.setMessage(id == null ? "Product created successfully" : "Product updated successfully");

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


}


