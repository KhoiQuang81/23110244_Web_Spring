package vn.iotstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.iotstar.entity.Product;
import vn.iotstar.service.ProductService;

import java.util.List;

public class LoginController {

    @Autowired
    private ProductService service;

    // Handler khi login thành công
    @PostMapping("/login_success_handler")
    public String loginSuccessHandler() {
        System.out.println("Logging user login success...");
        return "index"; // load trang index.html hoặc index.jsp
    }

    // Handler khi login thất bại
    @PostMapping("/login_failure_handler")
    public String loginFailureHandler() {
        System.out.println("Login failure handler....");
        return "login"; // load lại trang login
    }

    // Trang chủ hiển thị list sản phẩm
    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    // Thêm sản phẩm mới
    @RequestMapping("/new")
    public String showNewProductForm(Model model, @ModelAttribute("product") Product product) {
        model.addAttribute("product", product);
        return "new_product"; // view new_product.html/jsp
    }

    // Lưu sản phẩm
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);
        return "redirect:/";
    }

    // Sửa sản phẩm
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = service.get(id);
        mav.addObject("product", product);
        return mav;
    }

    // Xóa sản phẩm
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }
}
