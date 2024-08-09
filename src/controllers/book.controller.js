import Book from "../models/book.model.js";
import { BookDTO } from "../dtos/BookDTO.js";

/**
 * @route   POST /books
 * @desc    Create a new book
 * @access  Private
 * @param   {string} userId - ID of the authenticated user
 * @body    {string} name - Name of the book
 * @body    {string} author - Author of the book
 * @body    {number} publishedYear - Year the book was published
 * @returns {object} - Created book as BookDTO
 */
export const createBook = async (req, res) => {
    try {
        const { userId } = req;
        const { name, author, publishedYear } = req.body;

        const newBook = new Book({
            userId,
            name,
            author,
            publishedYear
        });

        const bookSave = await newBook.save();
        const bookDTO = new BookDTO(bookSave);

        res.status(201).json(bookDTO);

    } catch (error) {
        console.error(error.message);
        res.status(500).json({ message: "An error occurred while creating the book." });
    }
};

/**
 * @route   GET /books
 * @desc    Get all books for a user
 * @access  Private
 * @param   {string} userId - ID of the authenticated user
 * @returns {array} - List of books as BookDTO
 */
export const getAllBooks = async (req, res) => {
    try {
        const { userId } = req;

        const booksUser = await Book.find({ userId });

        if (booksUser.length === 0) {
            return res.status(200).json({ message: "The user does not have any books available." });
        }

        const booksDTO = booksUser.map(book => new BookDTO(book));
        res.status(200).json(booksDTO);

    } catch (error) {
        console.error(error.message);
        res.status(500).json({ message: "An error occurred while retrieving books." });
    }
};

/**
 * @route   GET /books/:id
 * @desc    Get a book by its ID
 * @access  Private
 * @param   {string} id - ID of the book to retrieve
 * @returns {object} - Book as BookDTO
 */
export const getBookById = async (req, res) => {
    try {
        const { id } = req.params;

        const bookFound = await Book.findById(id);

        if (!bookFound) {
            return res.status(404).json({ message: "Book not found." });
        }

        const bookDTO = new BookDTO(bookFound);
        res.status(200).json(bookDTO);

    } catch (error) {
        console.error(error.message);
        res.status(500).json({ message: "An error occurred while retrieving the book." });
    }
};

/**
 * @route   PUT /books/:id
 * @desc    Update a book by its ID
 * @access  Private
 * @param   {string} id - ID of the book to update
 * @body    {string} name - Updated name of the book
 * @body    {string} author - Updated author of the book
 * @body    {number} publishedYear - Updated year the book was published
 * @returns {object} - Updated book as BookDTO
 */
export const updateBook = async (req, res) => {
    try {
        const { id } = req.params;

        const bookFound = await Book.findByIdAndUpdate(id, req.body, { new: true });

        if (!bookFound) {
            return res.status(404).json({ message: "Book not found." });
        }

        const bookDTO = new BookDTO(bookFound);
        res.status(200).json(bookDTO);

    } catch (error) {
        console.error(error.message);
        res.status(500).json({ message: "An error occurred while updating the book." });
    }
};

/**
 * @route   DELETE /books/:id
 * @desc    Delete a book by its ID
 * @access  Private
 * @param   {string} id - ID of the book to delete
 * @returns {object} - Success message
 */
export const deleteBook = async (req, res) => {
    try {
        const { id } = req.params;

        const bookFound = await Book.findByIdAndDelete(id);

        if (!bookFound) {
            return res.status(404).json({ message: "Book not found." });
        }

        res.status(200).json({ message: "Book deleted successfully." });
        
    } catch (error) {
        console.error(error.message);
        res.status(500).json({ message: "An error occurred while deleting the book." });
    }
};
