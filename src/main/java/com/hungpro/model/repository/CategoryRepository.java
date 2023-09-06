package com.hungpro.model.repository;

import com.hungpro.model.entity.Blog;
import com.hungpro.model.entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryRepository implements ICategoryRepository<Category, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("SELECT C FROM Category AS C", Category.class).getResultList();
    }

    @Override
    public Category findByID(Long id) {
        return entityManager.createQuery("SELECT C FROM Category AS C WHERE C.id=:id", Category.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public void save(Category c) {
        if (c.getId() == null) {
            // thêm mới
            entityManager.persist(c);
        } else {
//            cập nhật
            entityManager.merge(c);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findByID(id));
    }
}
