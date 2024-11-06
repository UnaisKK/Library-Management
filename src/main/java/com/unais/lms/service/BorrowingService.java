package com.unais.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unais.lms.entity.Book;
import com.unais.lms.entity.Borrow;
import com.unais.lms.entity.Patron;
import com.unais.lms.exception.BookOutOfStockException;
import com.unais.lms.exception.BorrowNotFoundException;
import com.unais.lms.repository.BorrowRepository;

@Service
public class BorrowingService {

    @Autowired
    BookService bookService;
    @Autowired
    PatronService patronService;
    @Autowired
    BorrowRepository borrowRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Borrow borrowBook(Long bookId, Long patronId) {
        Book book = bookService.getBookById(bookId);
        Patron patron = patronService.getPatronById(patronId);
        if (book.canBorrow()) {
            book.borrow();
            Borrow borrow = new Borrow(book, patron);
            borrowRepository.save(borrow);
            return borrow;
        }
        throw new BookOutOfStockException(book);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void returnBook(Long bookId, Long patronId) {
        Borrow borrow = findIfAlreadyBorrowed(bookId, patronId);
        borrow.doReturn();
        borrowRepository.save(borrow);
    }

    private Borrow findIfAlreadyBorrowed(Long bookId, Long patronId) {
        return borrowRepository.findIfAlreadyBorrowed(bookId, patronId).stream().findAny().orElseThrow(() -> new BorrowNotFoundException(bookId, patronId));
    }

}
