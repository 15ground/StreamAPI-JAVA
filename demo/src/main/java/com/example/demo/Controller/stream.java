package com.example.demo.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.example.demo.Model.Products;
import com.example.demo.Untils.JacksonHelper;

public class stream {
    public static void docFileJson() {

        // String path = lab1.class.getResource(fileName).getPath();

        List<Products> listProduct = JacksonHelper.readJsonFileToObject(Products.class,
                "D:\\LAB1\\demo\\src\\main\\java\\com\\example\\demo\\Controller\\data.json");

        if (listProduct.size() > 0) {

            // List Product
            System.out.println("--- List Product ---");
            listProduct.stream().forEach(product -> {
                System.out.println(product.toString());
            });

            // Filter by date after "11/7/2021" and price > 100k
            System.out.println("--- Filter by: Date > 11/7/2021 and Price > 100000 ---");
            Date date;
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse("11/7/2021");
                listProduct.stream().filter(product -> product.getPrice().stream().anyMatch(gia -> gia > 100000)
                        && product.getDate().compareTo(date) > 0).forEach(System.out::println);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // sort by createDate
            System.out.println("--- Sort by: Created ---");
            listProduct.stream().sorted(Comparator.nullsLast((sp1, sp2) -> sp1.getDate().compareTo(sp2.getDate())))
                    .forEach(System.out::println);

            // avg price
            System.out.println("--- AVG by: Product's Price ---");
            listProduct.stream().forEach(product -> System.out.println("AVG " + product.getName() + ": "
                    + product.getPrice().stream().mapToDouble(Double::doubleValue).average().orElse(0.0)));
            // avg all
            Double avgAll = listProduct.stream()
                    .flatMapToDouble(product -> product.getPrice().stream().mapToDouble(Double::valueOf)).average()
                    .getAsDouble();
            System.out.println("--- AVG by: All Product's Price --- " + avgAll);

        }

    }
}
