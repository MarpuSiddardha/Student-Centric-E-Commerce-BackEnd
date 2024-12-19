package com.taskmanager.Controller;

import com.taskmanager.Entity.Cart;
import com.taskmanager.Entity.CartItem;
import com.taskmanager.Entity.Student;
import com.taskmanager.Service.CartService;
import com.taskmanager.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @Autowired
    private StudentService studentService; // Using StudentService instead of UserService

    @PostMapping("/add")
    public ResponseEntity<?> addItemToCart(@RequestParam Long studentId,
                                           @RequestParam Long itemId,
                                           @RequestParam int quantity) {
        // Use StudentService to fetch the student by ID
        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));
        Cart cart = cartService.addItemToCart(studentId, itemId, quantity); // No price parameter needed
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/view")
    public ResponseEntity<?> viewCart(@RequestParam Long studentId) {
        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));
        List<CartItem> items = cartService.viewCart(studentId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/view-all")
    public ResponseEntity<?> viewAllCarts() {
        try {
            List<Cart> carts = cartService.viewAllCarts();
            return ResponseEntity.ok(carts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }


    @DeleteMapping("/remove")
    public ResponseEntity<?> removeItem(@RequestParam Long studentId, @RequestParam Long itemId) {
        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));
        cartService.removeItemFromCart(student, itemId);
        return ResponseEntity.ok("Item removed");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(@RequestParam Long studentId) {
        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));
        cartService.clearCart(student);
        return ResponseEntity.ok("Cart cleared");
    }
}

//    @PostMapping("/place-order")
//    public ResponseEntity<String> placeOrder(@RequestParam Long studentId) {
//        try {
//            orderService.placeOrder(studentId);
//            return ResponseEntity.ok("Order placed successfully!");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to place the order: " + e.getMessage());
//        }
//    }
//}
