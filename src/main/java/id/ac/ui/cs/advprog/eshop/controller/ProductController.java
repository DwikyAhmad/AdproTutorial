package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "listProduct";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(Model model, @PathVariable("id") String id) {
        Product product = service.findProduct(Integer.parseInt(id));
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "editProduct";
    }

    @PutMapping("/edit/{id}")
    public String editProductPut(@PathVariable("id") String id, @ModelAttribute Product product) {
        service.editProduct(Integer.parseInt(id), product);
        return "redirect:/product/list";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        service.deleteProduct(Integer.parseInt(id));
        return "redirect:/product/list";
    }
}

