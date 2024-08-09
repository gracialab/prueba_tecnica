import Book from "../models/book.model.js";

export class BookService {
    /**
     * Creates a new book
     * @param {Object} bookData - Data for the new book
     * @param {string} bookData.userId - The ID of the user who owns the book
     * @param {string} bookData.name - The name of the book
     * @param {string} bookData.author - The author of the book
     * @param {number} bookData.publishedYear - The year the book was published
     * @returns {Promise<Book>} - The newly created book
     * @throws {Error} - Throws an error if book creation fails
     */
    async createBook(bookData) {
        // Create a new book instance with the provided data
        const newBook = Book(bookData);
        // Save the new book to the database
        return await newBook.save();
    }

    /**
     * Retrieves all books for a specific user
     * @param {string} userId - The ID of the user
     * @returns {Promise<Array<Book>>} - List of books owned by the user
     * @throws {Error} - Throws an error if retrieval fails
     */
    async getBooksByUserId(userId) {
        // Find and return all books that belong to the specified user
        return await Book.find({ userId });
    }

    /**
     * Retrieves a book by its ID
     * @param {string} bookId - The ID of the book
     * @returns {Promise<Book|null>} - The book if found, or null if not found
     * @throws {Error} - Throws an error if retrieval fails
     */
    async getBookById(bookId) {
        // Find and return the book by its ID
        return await Book.findById(bookId);
    }

    /**
     * Updates a book's information
     * @param {string} bookId - The ID of the book to update
     * @param {Object} updateData - The data to update
     * @param {string} [updateData.name] - The updated name of the book
     * @param {string} [updateData.author] - The updated author of the book
     * @param {number} [updateData.publishedYear] - The updated year of publication
     * @returns {Promise<Book|null>} - The updated book if successful, or null if not found
     * @throws {Error} - Throws an error if update fails
     */
    async updateBook(bookId, updateData) {
        // Find the book by ID and update it with the provided data
        return await Book.findByIdAndUpdate(bookId, updateData, { new: true });
    }

    /**
     * Deletes a book by its ID
     * @param {string} bookId - The ID of the book to delete
     * @returns {Promise<Book|null>} - The deleted book if successful, or null if not found
     * @throws {Error} - Throws an error if deletion fails
     */
    async deleteBook(bookId) {
        // Find the book by ID and delete it
        return await Book.findByIdAndDelete(bookId);
    }
}
