package com.example.bsp3admoon;

public class cart {
    private int cartID;
    private int quantity;

    public cart(){}

    public cart(int cartID, int quantity){

        this.cartID = cartID;
        this.quantity = quantity;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
