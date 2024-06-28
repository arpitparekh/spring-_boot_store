package com.arpit.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arpit.store.model.Message;
import com.arpit.store.model.User;

@Controller
public class MessageController {
    
    @GetMapping("/hello")
    @ResponseBody
    public String messageFromParameter(
            @RequestParam(name = "message", required = false, defaultValue = "Hello, World!") String message) {
        return "<html>" +
                "<head><title>Hello Message</title></head>" +
                "<body>" +
                "<h1>" + message + "</h1>" +
                "</body>" +
                "</html>";
    }

    @GetMapping("/hello-thyme")
    public String messageFromThymeleaf(
            @RequestParam(name = "message", required = false, defaultValue = "Hello, World!") String content,
            Model model) {
        Message msg = new Message();
        msg.setContent(content);
        model.addAttribute("message", msg);
        return "hello-thyme"; // This will map to src/main/resources/templates/hello-thyme.html
    }
    
    @GetMapping("/page-one")
    public String showForm(Model model) {
        model.addAttribute("message", new Message());
        return "page-one"; // This will map to src/main/resources/templates/page-one.html
    }

    @PostMapping("/page-one")
    public String handleFormSubmit(
            @RequestParam(name = "content", required = false, defaultValue = "Hello, World!") String content,
            Model model) {
        Message msg = new Message();
        msg.setContent(content);
        model.addAttribute("message", msg);
        return "page-two"; // This will map to src/main/resources/templates/page-two.html
    }
    
    @GetMapping("/source")
    public String showForm() {
        return "source";
    }

    @PostMapping("/destination")
    public String submitForm(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("age") int age,
            @RequestParam("message") String message,
            Model model) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        user.setMessage(message);
        
        model.addAttribute("user", user);
        return "destination";
    }

}
