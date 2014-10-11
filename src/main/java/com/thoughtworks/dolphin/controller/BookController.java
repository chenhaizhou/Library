package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.dto.BookSearchCondition;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.service.BookService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {

    private final Log logger = LogFactory.getLog(BookController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/addBook",method = RequestMethod.GET)
    public void addBook(){

    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    @ResponseBody
    public String addBook(@RequestBody Book book)
    {
        logger.info("enter addBook");
        int result = bookService.insertBook(book);
        String resultCode;
        if(result == 1){
            resultCode = "success";
        } else {
            resultCode = "fail";
        }
        JSONObject reponseCode = new JSONObject();
        reponseCode.put("resultCode", resultCode);
        reponseCode.put("coverImageId", String.valueOf(book.getCoverImageId()));

        return reponseCode.toString();
    }

    @RequestMapping(value = "/checkISBN", method = RequestMethod.GET)
    @ResponseBody
    public String checkISBN(String isbn) {
        logger.info("isbn:" + isbn);
        return String.valueOf(!bookService.isExist(isbn));

    }

    @RequestMapping(value = "/listBooks", method = RequestMethod.POST)
    @ResponseBody
    public List<Book> listBooks(@RequestBody BookSearchCondition condition) {
        return bookService.getBooks(condition);
    }

    @RequestMapping("/booksCount")
    @ResponseBody
    public int getTotalBookCount() {
        return bookService.getAllBookCount();
    }
}