package com.thoughtworks.dolphin.controller;

import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.common.SearchResult;
import com.thoughtworks.dolphin.dto.BookQuery;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.model.BorrowBook;
import com.thoughtworks.dolphin.service.BookService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class BookController {

    private static final Log LOGGER = LogFactory.getLog(BookController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    @ResponseBody
    public String addBook(@RequestBody Book book)
    {
        LOGGER.info("enter addBook");
        int result = bookService.insertBook(book);
        return generateResultCode(result);
    }

    @RequestMapping(value = "/checkISBN", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkISBN(String isbn, String bookId) {
        try {
            if (isAddBook(bookId)) {
                return !bookService.isExist(isbn);
            } else {
                return isIsbnValid(isbn, Integer.parseInt(bookId));
            }
        } catch (RuntimeException e) {
            return false;
        }
    }

    @RequestMapping(value = "/listBooks", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult<Book> listBooks(@RequestParam("pageNumber")int pageNumber,@RequestParam("keyword")String keyword) {
        BookQuery query = new BookQuery();
        query.setKeyword(keyword);
        query.setPageNumber(pageNumber);
        SearchResult<Book> result = bookService.getBooks(query);
        LOGGER.info("pageNumber:" + pageNumber + "; keyword:" + keyword + ";result size: " + result.getTotalCount());
        return result;
    }

    @RequestMapping(value = "/bookDetail", method = RequestMethod.GET)
    public String bookDetail(String bookId, ModelMap map){
        LOGGER.info("bookId:" + bookId);
        try {
            Book book = bookService.getBook(Integer.parseInt(bookId));
            map.put("book", book);
            return "book_detail";
        } catch (NumberFormatException e) {
            map.put("message", "No such book with id:" + bookId);
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/delbook", method = RequestMethod.POST)
    @ResponseBody
    public void deleteBook(@RequestBody String isbn,HttpServletRequest req){
        LOGGER.info("Delete Book: "+ isbn);
        String realPath = req.getSession().getServletContext().getRealPath(Constants.IMAGE_UPLOAD_RELATIVE_PATH);
        bookService.deleteBook(isbn,realPath);
    }

    @RequestMapping(value = "/editBook", method = RequestMethod.POST)
    @ResponseBody
    public String editBook(@RequestBody Book book){

        LOGGER.info("enter editBook");
        int result = bookService.updateBook(book);
        return generateResultCode(result);
    }

    private String generateResultCode(int result) {
        String resultCode;
        if(result == 1){
            resultCode = "success";
        } else {
            resultCode = "fail";
        }

        JSONObject reponseCode = new JSONObject();
        reponseCode.put("resultCode", resultCode);
        return reponseCode.toString();
    }

    private boolean isIsbnValid(String isbn, int bookId) {
        Book book = bookService.getBook(bookId);
        if(book.getIsbn().equals(isbn)){
            return true;
        } else {
            return !bookService.isExist(isbn);
        }
    }

    private boolean isAddBook(String bookId) {
        return bookId == null;
    }

    @RequestMapping(value ="/borrowBook" ,method = RequestMethod.POST)
    @ResponseBody
    public String borrowBook(@RequestBody Map map) {
        int bookId = Integer.valueOf((String)map.get("bookId"));
        String userName = (String) map.get("userName");

        String resultCode = bookService.borrowBook(bookId, userName);
        JSONObject reponseCode = new JSONObject();
        reponseCode.put("resultCode", resultCode);
        return reponseCode.toString();
    }

    @RequestMapping(value = "/borrowStatus", method = RequestMethod.GET)
    public String bookStatus(){
        try {
            return "borrow_status";
        } catch (NumberFormatException e) {
            return "redirect:error";
        }
    }

    @RequestMapping(value ="/borrowedBookListCount", method = RequestMethod.GET)
    @ResponseBody
    public int borrowedBookListCount(String username, String status){
        LOGGER.info("enter borrowedBookListCount");
        LOGGER.info("status:" + status);

        return bookService.getBorrowedBookListCount(username, status);
    }

    @RequestMapping(value ="/borrowedBooksList", method = RequestMethod.GET)
    @ResponseBody
    public List<BorrowBook> borrowedBooksList(String username, String pagenumber, String status){

        return bookService.getBorrowedBookList(username, pagenumber, status);

    }

}