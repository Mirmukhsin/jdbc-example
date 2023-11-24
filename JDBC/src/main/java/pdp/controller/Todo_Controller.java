package pdp.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pdp.service_with_JDBCTemplate.Todo_Service_with_JDBCTemplate;
import pdp.Todo;

@Controller
public class Todo_Controller {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml-config.xml");
    Todo_Service_with_JDBCTemplate service = context.getBean(Todo_Service_with_JDBCTemplate.class);
//    Todo_Service_NamedParameter_JDBC service = context.getBean(Todo_Service_NamedParameter_JDBC.class);

    @GetMapping("/")
    public ModelAndView todos(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("todos");
    modelAndView.addObject("todos",service.getAll());
    return modelAndView;
    }
    @GetMapping("/addTodo")
    public ModelAndView addTodo(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("addTodo");
    modelAndView.addObject("todo",new Todo());
    return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("todo") Todo todo){
    ModelAndView modelAndView = new ModelAndView();
    service.save(todo);
//    service.save_withSimpleJdbcInsert(todo);
    modelAndView.setViewName("redirect:/");
    return modelAndView;
    }
    @GetMapping("/updateTodo/{id}")
    public ModelAndView updateTodo(@PathVariable(value = "id") int id){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("update");
    modelAndView.addObject("todo",service.get(id));
        System.out.println(service.get(id));
    return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute(value = "todo") Todo todo){
    ModelAndView modelAndView = new ModelAndView();
        System.out.println(todo);
    service.update(todo);
    modelAndView.setViewName("redirect:/");
    return modelAndView;
    }
    @GetMapping("/deleteTodo/{id}")
    public ModelAndView delete(@PathVariable(value = "id") Integer id){
    ModelAndView modelAndView = new ModelAndView();
    service.delete(id);
    modelAndView.setViewName("redirect:/");
    return modelAndView;
    }
}
