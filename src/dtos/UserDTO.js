/**
 * Data Transfer Object (DTO) for representing user data in a simplified format.
 * 
 * The `UserDTO` class is used to create a standardized representation of a user object,
 * which can be safely exposed in responses or logs. It extracts and maps the relevant
 * fields from a user model instance to a simplified object format.
 * 
 * @class
 */
export class UserDTO {
    /**
     * Creates an instance of UserDTO.
     * 
     * @param {Object} user - The user object to be transformed.
     * @param {string} user._id - The unique identifier for the user.
     * @param {string} user.username - The username of the user.
     * @param {string} user.email - The email address of the user.
     * 
     * @example
     * const user = { _id: '12345', username: 'john_doe', email: 'john.doe@example.com' };
     * const userDTO = new UserDTO(user);
     * console.log(userDTO);
     * // Output: { id: '12345', username: 'john_doe', email: 'john.doe@example.com' }
     */
    constructor({ _id, username, email }) {
        this.id = _id; // Unique identifier of the user
        this.username = username; // Username of the user
        this.email = email; // Email address of the user
    }
}
