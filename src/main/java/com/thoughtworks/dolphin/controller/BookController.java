package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiezhou on 9/28/14.
 */
@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "addBook", method = RequestMethod.POST)
    public  @ResponseBody
    Map<String, Object> addBook(@RequestBody Book book)
    {
        System.out.println("------------book-----------");

        Map<String,Object> resultMap = new HashMap<String,Object>();

        resultMap.put("resultCode", "success");
        return resultMap;
    }



}
