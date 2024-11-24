package khuong.com.midterm_java.service;

import khuong.com.midterm_java.dto.CategoryDTO;
import khuong.com.midterm_java.entity.Category;
import khuong.com.midterm_java.repository.CategoryRepository;
import khuong.com.midterm_java.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryService {
  @Autowired
  private final CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

  public List<CategoryDTO> mapToDto(List<Category> categories) {
    List<CategoryDTO> dtos = new ArrayList<>();
    for (Category category : categories) {
      dtos.add(new CategoryDTO(
              category.getId(),
              category.getName()
      ));
    }
    return dtos;
  }

  public List<CategoryDTO> getAll() {
    List<Category> categories = categoryRepository.findAll();
    return mapToDto(categories);
  }

  public void create(CategoryDTO dto) {
    Category category = new Category();
    category.setId(dto.getId());
    category.setName(dto.getName());
    categoryRepository.save(category);
  }

  public void update(Long id, CategoryDTO dto) {
    Category category = categoryRepository.findById(id).orElse(null);
    category.setName(dto.getName());
    categoryRepository.save(category);
  }

  public void delete(Long id) {
    categoryRepository.deleteById(id);
  }
}








