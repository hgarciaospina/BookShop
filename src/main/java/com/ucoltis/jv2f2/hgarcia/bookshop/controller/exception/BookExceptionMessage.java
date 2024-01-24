package com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception;

public class BookExceptionMessage {
	
	// Invalid parameter error
    public static final String INVALID_PARAMETER_CODE = "301";
    public static final String INVALID_PARAMETER_MESSAGE = "The parameter is not valid";

    // Book errors
    public static final String BOOK_INVALID_PRICE_CODE = "302";
    public static final String BOOK_INVALID_PRICE_MESSAGE = "The price must be a value greater than zero";

    public static final String BOOK_PRICE_REQUIRED_CODE = "303";
    public static final String BOOK_PRICE_REQUIRED_MESSAGE = "Book price is required";

    public static final String BOOK_ISBN_REQUIRED_CODE = "304";
    public static final String BOOK_ISBN_REQUIRED_MESSAGE = "Book ISBN is required";

    public static final String BOOK_PAGES_REQUIRED_CODE = "305";
    public static final String BOOK_PAGES_REQUIRED_MESSAGE = "The number of pages is required";

    public static final String BOOK_RELEASE_DATE_REQUIRED_CODE = "306";
    public static final String BOOK_RELEASE_DATE_REQUIRED_MESSAGE = "The release date is required";

    public static final String BOOK_INVALID_PAGES_CODE = "307";
    public static final String BOOK_INVALID_PAGES_MESSAGE = "The number of pages must be greater than zero";

    public static final String BOOK_INFO_REQUIRED_CODE = "308";
    public static final String BOOK_INFO_REQUIRED_MESSAGE = "Book information is required";

    public static final String BOOK_NOT_FOUND_CODE = "309";
    public static final String BOOK_NOT_FOUND_MESSAGE = "Book not found with the provided id: ";

    public static final String BOOKS_NOT_FOUND_CODE = "310";
    public static final String BOOKS_NOT_FOUND_MESSAGE = "Books not found.";

    public static final String BOOK_TITLE_REQUIRED_CODE = "311";
    public static final String BOOK_TITLE_REQUIRED_MESSAGE = "Book title is required";

    // Category errors
    public static final String CATEGORY_INFO_REQUIRED_CODE = "312";
    public static final String CATEGORY_INFO_REQUIRED_MESSAGE = "Category information is required";

    public static final String CATEGORY_NOT_FOUND_CODE = "313";
    public static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found with the provided id: ";

    public static final String CATEGORY_INVALID_ID_CODE = "314";
    public static final String CATEGORY_INVALID_ID_MESSAGE = "The category id must be greater than zero and exist in the categories.";

    // Author errors
    public static final String AUTHOR_INVALID_ID_CODE = "315";
    public static final String AUTHOR_INVALID_ID_MESSAGE = "The author id must be greater than zero and exist in the authors.";

    public static final String AUTHOR_INFO_REQUIRED_CODE = "316";
    public static final String AUTHOR_INFO_REQUIRED_MESSAGE = "Author information is required";

    public static final String AUTHOR_NOT_FOUND_CODE = "317";
    public static final String AUTHOR_NOT_FOUND_MESSAGE = "Author not found with the provided id: ";

	
}
