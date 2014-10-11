package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.dto.BookSearchCondition;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.service.BookService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {

    private static final Log LOGGER = LogFactory.getLog(BookController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/addBook",method = RequestMethod.GET)
    public void addBook(){

    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    @ResponseBody
    public String addBook(@RequestBody Book book)
    {
        LOGGER.info("enter addBook");
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
        return String.valueOf(!bookService.isExist(isbn));

    }

    @RequestMapping(value = "/listBooks", method = RequestMethod.POST)
    @ResponseBody
    public List<Book> listBooks(@RequestBody BookSearchCondition condition) {
        return bookService.getBooks(condition);
    }

    @RequestMapping(value = "/booksCount", method = RequestMethod.POST)
    @ResponseBody
    public int getBookCount(@RequestBody BookSearchCondition condition) {
        return bookService.getBookCount(condition);
    }

    @RequestMapping(value = "/bookDetail", method = RequestMethod.GET)
    public String bookDetail(String bookId, ModelMap map){

        LOGGER.info("bookId:" + bookId);
        Book book = bookService.getBook(bookId);

        map.put("book", book);
        return "book_detail";
    }
}