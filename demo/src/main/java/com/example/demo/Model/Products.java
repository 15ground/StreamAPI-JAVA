package com.example.demo.Model;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.text.SimpleDateFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Products {
    String name;
    List<Double> price;
    String createdDate;
    Category category;

    public Date getDate() {
        return Date.from(Instant.parse(createdDate));
    }

    @Override
    public String toString() {
        return "Sản phẩm: " + name + ", giá bán: " + Arrays.toString(price.toArray()) + ", ngày tạo: "
                + new SimpleDateFormat("dd/MM/yyyy").format(getDate()) + " - Danh mục: " + category.getId()
                + ", tên danh mục: " + category.getName();
    }
}
