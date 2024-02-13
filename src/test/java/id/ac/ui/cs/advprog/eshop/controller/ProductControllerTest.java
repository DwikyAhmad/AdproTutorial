package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testRenderCreatePage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"));
    }

    @Test
    void testCreatePostRedirect() throws Exception {
        mockMvc.perform(post("/product/create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

    }

    @Test
    void testRenderProductListPage() throws Exception {
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("listProduct"));
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        when(productService.findProduct(0)).thenReturn(product);

        mockMvc.perform(get("/product/edit/0"))
                .andExpect(view().name("editProduct"));
    }

    @Test
    void testPutEditProductRedirect() throws Exception {
        mockMvc.perform(put("/product/edit/0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }


    @Test
    void testDeleteProductRedirect() throws Exception {
        mockMvc.perform(delete("/product/0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }
}
