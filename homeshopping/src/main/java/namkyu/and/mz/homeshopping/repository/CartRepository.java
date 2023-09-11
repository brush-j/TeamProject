package namkyu.and.mz.homeshopping.repository;

import namkyu.and.mz.homeshopping.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByMemberId(Long memberId);
}
