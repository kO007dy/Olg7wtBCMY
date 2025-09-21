// 代码生成时间: 2025-09-21 10:58:10
package com.example.demo.service;

import com.example.demo.domain.CartItem;
import com.example.demo.domain.ShoppingCart;
import com.example.demo.exception.ShoppingCartException;
import com.example.demo.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for shopping cart functionality.
 */
@Service
public class ShoppingCartService {

    /**
     * Injecting ShoppingCartRepository for data access.
     */
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    /**
     * Adds an item to the shopping cart.
     *
     * @param cartId The ID of the shopping cart.
     * @param itemId The ID of the item to be added.
     * @return The updated shopping cart.
     * @throws ShoppingCartException If cartId or itemId is invalid.
     */
    @Transactional
    public ShoppingCart addItemToCart(String cartId, String itemId) throws ShoppingCartException {
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartException("Shopping cart not found"));

        if (!cart.getItems().containsKey(itemId)) {
            CartItem item = new CartItem(itemId, 1);
            cart.getItems().put(itemId, item);
        } else {
            cart.getItems().get(itemId).incrementQuantity();
        }

        return shoppingCartRepository.save(cart);
    }

    /**
     * Removes an item from the shopping cart.
     *
     * @param cartId The ID of the shopping cart.
     * @param itemId The ID of the item to be removed.
     * @return The updated shopping cart.
     * @throws ShoppingCartException If cartId or itemId is invalid.
     */
    @Transactional
    public ShoppingCart removeItemFromCart(String cartId, String itemId) throws ShoppingCartException {
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartException("Shopping cart not found"));

        if (cart.getItems().containsKey(itemId)) {
            if (cart.getItems().get(itemId).getQuantity() > 1) {
                cart.getItems().get(itemId).decrementQuantity();
            } else {
                cart.getItems().remove(itemId);
            }
        }

        return shoppingCartRepository.save(cart);
    }

    /**
     * Returns the shopping cart for a given cart ID.
     *
     * @param cartId The ID of the shopping cart.
     * @return The shopping cart details.
     * @throws ShoppingCartException If cartId is invalid.
     */
    public ShoppingCart getShoppingCart(String cartId) throws ShoppingCartException {
        return shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartException("Shopping cart not found"));
    }

    /**
     * Lists all items in a shopping cart.
     *
     * @param cartId The ID of the shopping cart.
     * @return A list of all items in the cart.
     * @throws ShoppingCartException If cartId is invalid.
     */
    public List<CartItem> listCartItems(String cartId) throws ShoppingCartException {
        ShoppingCart cart = getShoppingCart(cartId);
        return cart.getItems().values().stream().collect(Collectors.toList());
    }
}
