package eg.gov.iti.jets.foodie.home.model;

import java.util.List;

import eg.gov.iti.jets.foodie.model.Category;

public class CategoryResponse {

    private List<Category> categories;

    public CategoryResponse(List<Category> categories) {
        this.categories = categories;
    }

    public CategoryResponse() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
