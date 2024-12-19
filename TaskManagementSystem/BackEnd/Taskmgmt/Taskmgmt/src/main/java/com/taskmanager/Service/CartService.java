package com.taskmanager.Service;

import com.taskmanager.Entity.Cart;
import com.taskmanager.Entity.CartItem;
import com.taskmanager.Entity.Item;
import com.taskmanager.Entity.Student;
import com.taskmanager.Repository.CartRepository;
import com.taskmanager.Repository.ItemRepository;
import com.taskmanager.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Repo studentRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Cart addItemToCart(Long studentId, Long itemId, int quantity) {
        // Fetch the Student by ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // Fetch the Item by ID to get its current price
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        // Now pass the student object to findByStudent
        Cart cart = cartRepository.findByStudent(student).orElse(new Cart());
        cart.setStudent(student);

        // Create a new CartItem and set the price from the Item entity
        CartItem cartItem = new CartItem();
        cartItem.setItemId(itemId);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(item.getPrice());  // Set price from Item
        cartItem.setCart(cart);

        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }



    public List<CartItem> viewCart(Long studentId) {
        // Fetch the Student by ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // Now pass the student object to findByStudent
        Cart cart = cartRepository.findByStudent(student)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for student"));
        return cart.getItems();
    }


    public void removeItemFromCart( Student student, Long itemId) {
        Cart cart = cartRepository.findByStudent(student)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for student"));

        cart.getItems().removeIf(item -> item.getItemId().equals(itemId));
        cartRepository.save(cart);
    }

    public void clearCart(Student student) {
        Cart cart = cartRepository.findByStudent(student)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for student"));

        cart.getItems().clear();
        cartRepository.save(cart);
    }

    public List<Cart> viewAllCarts() {
        // Fetch all carts with their items
        List<Cart> carts = cartRepository.findAll();
        if (carts.isEmpty()) {
            throw new IllegalArgumentException("No carts found.");
        }
        return carts;
    }



//    public void placeOrder(Student student) {
//        Cart cart = cartRepository.findByStudent(student)
//                .orElseThrow(() -> new RuntimeException("Cart not found for student ID: " + student));
//
//        if (cart.getItems().isEmpty()) {
//            throw new RuntimeException("Cart is empty.");
//        }
//
//        Order order = new Order();
//        order.setStudent(student);
//        order.setOrderDate(LocalDateTime.now());
//        order.setStatus("PENDING");
//        order.setItems(new ArrayList<>(cart.getItems())); // Copy cart items to order items
//
//        orderRepository.save(order);
//
//        // Clear the cart
//        cart.getItems().clear();
//        cartRepository.save(cart);
//    }

}
