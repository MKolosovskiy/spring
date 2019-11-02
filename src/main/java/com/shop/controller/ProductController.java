package com.shop.controller;

import com.shop.model.Product;
import com.shop.dto.ProductDTO;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String add() {
        return "add_product";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ProductDTO productDTO, Model model) {
        Product product = new Product(productDTO);
        if (product.getName().isEmpty() || product.getDescription().isEmpty()
                || product.getPrice() < 0) {
            model.addAttribute("addProductMessage", "The price must be bigger or equals 0");
            productService.getLines(product, model);
            return "add_product";
        } else {
            productService.add(product);
            return "redirect:/admin/product/all";
        }
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        model.addAttribute("id", id);
        Optional<Product> optionalProduct = productService.getById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productService.getLines(product, model);
            return "edit_product";
        }
        return "error";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute ProductDTO productDTO, @RequestParam Long id, Model model) {
        Product product = new Product(productDTO);
        model.addAttribute("id", id);
        if (product.getName().isEmpty() || product.getDescription().isEmpty()) {
            model.addAttribute("editProductMessage", "An empty field/s");
            productService.getLines(product, model);
            return "edit_product";
        } else if (product.getPrice() < 0) {
            model.addAttribute("editProductMessage", "The price must be bigger or equals 0");
            productService.getLines(product, model);
            return "edit_product";
        } else {
            productService.edit(product);
            return "redirect:/admin/product/all";
        }
    }

    @GetMapping("/all")
    public String products(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam Long id) {
        Optional<Product> optionalProduct = productService.getById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productService.remove(product);
            return "redirect:/admin/product/all";
        }
        return "error";
    }
}
