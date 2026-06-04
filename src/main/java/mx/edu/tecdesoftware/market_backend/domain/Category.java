package mx.edu.tecdesoftware.market_backend.domain;

import mx.edu.tecdesoftware.market_backend.persistence.entity.Compra;

public class Category {
    private int CategoryId;
    private String Category;
    private boolean Active;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }
}
