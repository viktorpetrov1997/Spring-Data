package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>
{
    List<Ingredient> findByNameStartingWith(String start);

    List<Ingredient> findByNameInOrderByPrice(List<String> names);

    void deleteByName(String name);

    @Query("UPDATE Ingredient AS i SET i.price = i.price * 1.1")
    @Modifying
    @Transactional
    void updatePriceByPercent();

    @Query("UPDATE Ingredient AS i SET i.price = i.price * (1 + :percent / 100) WHERE i.name IN :names")
    @Modifying
    @Transactional
    void updatePriceByName(double percent, List<String> names);
}
