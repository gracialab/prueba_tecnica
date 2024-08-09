import User from "../models/user.model.js";
import bcrypt from "bcryptjs";
import { createAccessToken } from "../lib/createToken.js";

export class AuthService {
    /**
     * Registers a new user
     * @param {Object} params - Parameters for registration
     * @param {string} params.username - The username of the new user
     * @param {string} params.email - The email of the new user
     * @param {string} params.password - The password of the new user
     * @returns {Promise<User>} - The newly created user
     * @throws {Error} - Throws an error if registration fails
     */
    async registerUser({ username, email, password }) {
        // Hash the user's password
        const passwordHash = await bcrypt.hash(password, 12);
        // Create a new user instance
        const newUser = User({ username, email, password: passwordHash });
        // Save the new user to the database
        return await newUser.save();
    }

    /**
     * Logs in an existing user
     * @param {Object} params - Parameters for login
     * @param {string} params.email - The email of the user
     * @param {string} params.password - The password of the user
     * @returns {Promise<User|null>} - The user if login is successful, or null if credentials are invalid
     * @throws {Error} - Throws an error if login fails
     */
    async loginUser({ email, password }) {
        // Find the user by email
        const userFound = await User.findOne({ email });

        // Check if user exists
        if (!userFound) return null;

        // Compare the provided password with the stored password
        const passwordValid = await bcrypt.compare(password, userFound.password);
        if (!passwordValid) return null;

        return userFound;
    }

    /**
     * Creates an access token for the user
     * @param {User} user - The user for whom the token is created
     * @returns {Promise<string>} - The generated access token
     * @throws {Error} - Throws an error if token creation fails
     */
    async createToken(user) {
        // Create and return an access token
        return await createAccessToken({ id: user._id });
    }

    /**
     * Retrieves a user profile from the database based on the provided user ID.
     *
     * @param {string} userId - The ID of the user whose profile is to be retrieved. This should be a valid MongoDB ObjectId.
     * @returns {Promise<Object|null>} - A promise that resolves to the user profile object if found, or null if no user is found with the provided ID.
     * 
     * @throws {Error} - Throws an error if there is a problem with the database query.
     */
    async getUserProfile(userId) {
        return await User.findById(userId);
    }
}
