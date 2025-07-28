package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long>
{
    List<Shampoo> findBySize(Size size);

    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, int labelId);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int countByPriceLessThan(BigDecimal price);

    List<Shampoo> findByIngredientsNameIn(List<String> names);

    @Query("SELECT s FROM Shampoo AS s JOIN s.ingredients AS i WHERE i.name IN :names")
    List<Shampoo> findByIngredientsNameInQuery(List<String> names);

    @Query("SELECT s FROM Shampoo AS s WHERE size(s.ingredients) < :count")
    List<Shampoo> findByIngredientsCountLessThan(int count);
}
