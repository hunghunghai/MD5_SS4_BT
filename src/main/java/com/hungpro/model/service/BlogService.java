package com.hungpro.model.service;

import com.hungpro.model.entity.Blog;
import com.hungpro.model.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService<Blog, Long> {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findByID(Long id) {
        return blogRepository.findByID(id);
    }

    @Override
    public void save(Blog b) {
        blogRepository.save(b);
    }

    @Override
    public void delete(Long id) {
        blogRepository.delete(id);
    }
}
