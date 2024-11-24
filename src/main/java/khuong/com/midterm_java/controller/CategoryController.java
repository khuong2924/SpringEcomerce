package khuong.com.midterm_java.controller;


import khuong.com.midterm_java.dto.CategoryDTO;
import khuong.com.midterm_java.dto.ResponseDTO;
import khuong.com.midterm_java.entity.Category;
import khuong.com.midterm_java.repository.CategoryRepository;
import khuong.com.midterm_java.service.CategoryService;
import khuong.com.midterm_java.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private static final Logger logger = Logger.getLogger(CategoryController.class.getName());

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseDTO<List<CategoryDTO>> getCategories() {
        ResponseDTO<List<CategoryDTO>> response = new ResponseDTO<>();
        response.setData(categoryService.getAll());
        response.setStatus(200);
        return response;
    }

    @PutMapping
    public ResponseDTO updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO) {
        categoryService.update(id, categoryDTO);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thành công category")
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseDTO<Void> delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Xóa thành công category")
                .build();
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseDTO<Void>> createOrUpdateCategory(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam("name") String name
    ) {
        Category category;

        if (id != null) {
            // Nếu id được truyền, tìm Category để cập nhật
            category = categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
            category.setName(name);
        } else {
            // Nếu không có id, tạo mới Category
            category = new Category();
            category.setName(name);
        }

        categoryRepository.save(category);

        ResponseDTO<Void> dto = new ResponseDTO<>();
        dto.setStatus(200);
        dto.setMessage(id == null ? "Category created successfully" : "Category updated successfully");

        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }







}




