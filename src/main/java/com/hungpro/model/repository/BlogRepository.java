package com.hungpro.model.repository;

import com.hungpro.model.entity.Blog;
import com.hungpro.model.entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BlogRepository implements IBlogRepository<Blog, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Blog> findAll() {
        return entityManager.createQuery("SELECT B FROM Blog AS B", Blog.class).getResultList();
    }

    @Override
    public Blog findByID(Long id) {
        return entityManager.createQuery("SELECT B FROM Blog AS B WHERE B.id=:id", Blog.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public void save(Blog b) {
        if (b.getId() == null) {
            // thêm mới
            entityManager.persist(b);
        } else {
//            cập nhật
            entityManager.merge(b);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findByID(id));
    }

    public List<Blog> findByCategory(Category category) {
        return entityManager.createQuery("SELECT b FROM Blog b WHERE b.category = :category", Blog.class)
                .setParameter("category", category).getResultList();
    }
}
