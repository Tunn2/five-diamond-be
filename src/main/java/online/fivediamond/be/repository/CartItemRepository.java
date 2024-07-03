package online.fivediamond.be.repository;

import online.fivediamond.be.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    //Transactional
    @Transactional
    @Modifying
    @Query(value = "delete from cart_item where id = :id", nativeQuery = true)
    void deleteCartItem(@Param("id") long id);
}
