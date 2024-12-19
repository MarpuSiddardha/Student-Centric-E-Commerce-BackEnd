package com.taskmanager.Repository;

import com.taskmanager.Entity.Cart;
import com.taskmanager.Entity.CartItem;
import com.taskmanager.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByStudent(Student student);
//    List<CartItem> findCartItemsByStudentId(Long studentId);
//    void clearCart(Student student);
}
