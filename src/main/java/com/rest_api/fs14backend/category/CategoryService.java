package com.rest_api.fs14backend.category;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookRepository;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;



    /**
     * implements methods called by the controller
     */
    //FIND ALL CATEGORIES
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    //FIND A CATEGORY BY ID
    public Category findById(UUID id){
        return categoryRepository.findById(id).orElseThrow(()->new NotFoundException("the id of the category cannot be found"));
    }
    //DELETE A CATEGORY BY ID
    public void deleteById(UUID id){
        try{
            categoryRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new NotFoundException("not found");
        }
    }
    //CREATE A CATEGORY
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    //ADDS A BOOK TO A CATEGORY
    public void addBookToCategory(UUID categoryId, UUID bookId) {
        System.out.println("addBookToCategoryIs called");
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found with ID " + categoryId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found with ID " + bookId));
        category.addBook(book);
        categoryRepository.save(category);
        book.setCategory(category);
        bookRepository.save(book);
    }
}
