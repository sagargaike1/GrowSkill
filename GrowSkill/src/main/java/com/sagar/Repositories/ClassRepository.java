package com.sagar.Repositories;

import com.sagar.Model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class ,Long> {
}
