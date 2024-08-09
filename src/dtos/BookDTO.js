/**
 * Data Transfer Object (DTO) for representing book data in a simplified format.
 * 
 * The `BookDTO` class is used to create a standardized representation of a book object,
 * which can be safely exposed in responses or logs. It extracts and maps the relevant
 * fields from a book model instance to a simplified object format.
 * 
 * @class
 */
export class BookDTO {
    /**
     * Creates an instance of BookDTO.
     * 
     * @param {Object} book - The book object to be transformed.
     * @param {string} book._id - The unique identifier for the book.
     * @param {string} book.userId - The ID of the user associated with the book.
     * @param {string} book.name - The name of the book.
     * @param {string} book.author - The author of the book.
     * @param {number} book.publishedYear - The year the book was published.
     * 
     * @example
     * const book = { _id: 'abc123', userId: 'user789', name: 'Book Title', author: 'Author Name', publishedYear: 2023 };
     * const bookDTO = new BookDTO(book);
     * console.log(bookDTO);
     * // Output: { _id: 'abc123', userId: 'user789', name: 'Book Title', author: 'Author Name', publishedYear: 2023 }
     */
    constructor({ _id, userId, name, author, publishedYear }) {
        this._id = _id; // Unique identifier of the book
        this.userId = userId; // ID of the user associated with the book
        this.name = name; // Name of the book
        this.author = author; // Author of the book
        this.publishedYear = publishedYear; // Year the book was published
    }
}
