package com.hungpro.controller;


import com.hungpro.model.entity.Blog;
import com.hungpro.model.entity.Category;
import com.hungpro.model.repository.BlogRepository;
import com.hungpro.model.repository.CategoryRepository;
import javafx.scene.web.HTMLEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api")
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/posts")
    public List<Blog> findAllBlog() {
        return blogRepository.findAll();
    }

    @GetMapping("/category")
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @GetMapping("/category/{categoryId}/posts")
    public List<Blog> getBlogsByCategory(@PathVariable Long categoryId) {
        // Truy vấn danh sách các bài viết thuộc một category cụ thể
        Category category = categoryRepository.findByID(categoryId);
        if (category == null) {
            return new ArrayList<>();
        }
        List<Blog> blogsInCategory = blogRepository.findByCategory(category);
        return blogsInCategory;
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Blog> getPostDetails(@PathVariable Long postId) {
        Blog blog = blogRepository.findByID(postId);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }
}
