package com.example.prigonews;

public class Hori_data_class {
    private int image_id;
    private String category;

    public Hori_data_class(int image_id, String category) {
        this.image_id = image_id;
        this.category = category;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
