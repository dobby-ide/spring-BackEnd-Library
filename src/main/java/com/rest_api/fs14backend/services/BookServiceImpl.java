package com.rest_api.fs14backend.services;

//@Service
//public class BookService {
//    @Autowired
//    private BookRepository bookRepository;
//
//    //Creates here methods that will be called from Endpoints in BookController
//    //FIND ALL BOOKS
//    public List<Book> findAll(){
//        return bookRepository.findAll();
//    }
//    //FIND A BOOK BY ID
//    public Book findById(UUID id){
//        return bookRepository.findById(id).orElseThrow(()-> new NotFoundException("The id of the book is not matching"));
//    }
//    //DELETE A BOOK BY ID
//    public void deleteById(UUID id){
//        try{
//            bookRepository.deleteById(id);
//        } catch(EmptyResultDataAccessException e){
//            throw new BookNotFoundException("Book with id " + id + " was not found, unfortunately");
//        } catch (DataIntegrityViolationException e){
//            throw new BookDeleteException("The book with id:"+ id +",thereafter could not be deleted");
//        }
//
//    }
//
//    //CREATE A BOOK
//    public Book createOne(Book book){
//        //validateBook(book);
//        return bookRepository.save(book);
//    }
//
//    //private void validateBook--if book does not contain a title...
//}
