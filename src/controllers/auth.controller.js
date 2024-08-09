import { AuthService } from "../services/auth.service.js";
import { UserDTO } from "../dtos/UserDTO.js";

const authService = new AuthService();

/**
 * @route   POST /register
 * @desc    Register a new user
 * @access  Public
 * @body    {string} username - The username of the new user
 * @body    {string} email - The email of the new user
 * @body    {string} password - The password of the new user
 * @returns {object} - Registered user as UserDTO and sets a cookie with the token
 */
export const registerUser = async (req, res) => {
    try {
        const { username, email, password } = req.body;
        const newUser = await authService.registerUser({ username, email, password });

        const token = await authService.createToken(newUser);
        res.cookie("token", token);

        const userDTO = new UserDTO(newUser);
        res.status(200).json(userDTO);

    } catch (error) {
        console.error(error.message);
        res.status(500).json({ message: "An error occurred while registering the user." });
    }
};

/**
 * @route   POST /login
 * @desc    Log in an existing user
 * @access  Public
 * @body    {string} email - The email of the user
 * @body    {string} password - The password of the user
 * @returns {object} - Logged-in user as UserDTO and sets a cookie with the token
 */
export const loginUser = async (req, res) => {
    try {
        const { email, password } = req.body;
        const userFound = await authService.loginUser({ email, password });

        if (!userFound) return res.status(404).json({ message: "User not found or invalid credentials" });

        const token = await authService.createToken(userFound);
        res.cookie("token", token);

        const userDTO = new UserDTO(userFound);
        res.status(200).json(userDTO);

    } catch (error) {
        console.error(error.message);
        res.status(500).json({ message: "An error occurred while logging in the user." });
    }
};

/**
 * @route   GET /profile
 * @desc    Get the logged-in user's profile
 * @access  Private
 * @header  {string} token - JWT token for authentication
 * @returns {object} - User profile as UserDTO
 */
export const profile = async (req, res) => {
    try {
        const { userId } = req;
        const userFound = await authService.getUserProfile(userId);

        if (!userFound) return res.status(401).json({ message: "Unauthorized" });

        const userDTO = new UserDTO(userFound);
        res.status(200).json(userDTO);
        
    } catch (error) {
        console.error(error.message);
        res.status(500).json({ message: "An error occurred while retrieving the profile." });
    }
};

/**
 * @route   POST /logout
 * @desc    Log out the current user
 * @access  Private
 * @returns {object} - Success message and clears the token cookie
 */
export const logout = async (req, res) => {
    res.cookie("token", "", {
        expires: new Date(0),
    });

    res.status(200).json({ message: "Logout success" });
};
