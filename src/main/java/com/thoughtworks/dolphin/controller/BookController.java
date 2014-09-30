package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiezhou on 9/28/14.
 */
@Controller
@RequestMapping("/")
public class BookController {

//    private static Logger LOG = Logger.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    @ResponseBody
    public String addBook(@RequestBody Book book)
    {
        System.out.println("------------book-----------");

        Map<String,Object> resultMap = new HashMap<String,Object>();

        resultMap.put("resultCode", "success");
        return "";
    }

    @RequestMapping(value = "/listBooks", method = RequestMethod.POST)
    @ResponseBody
    public List<Book> listBooks(@RequestBody String pageNum) {
        int pageIndex = Integer.parseInt(pageNum) - 1;
        return bookService.getBooks(Constants.ITEM_COUNT_IN_EACH_PAGE * pageIndex, Constants.ITEM_COUNT_IN_EACH_PAGE);
    }

    @RequestMapping("/booksCount")
    @ResponseBody
    public int getTotalBookCount() {
        return bookService.getAllBookCount();
    }
}