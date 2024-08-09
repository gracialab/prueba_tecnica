import bcrypt from "bcryptjs";
import User from "../models/user.model.js";
import { createAccessToken } from "../lib/createToken.js";
import { UserDTO } from "../dtos/UserDTO.js";

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
        const passwordHash = await bcrypt.hash(password, 12);

        const newUser = User({
            username,
            email,
            password: passwordHash,
        });

        const userSave = await newUser.save();

        const token = await createAccessToken({ id: userSave._id });
        res.cookie("token", token);

        const userDTO = new UserDTO(userSave);

        res.status(200).json(userDTO);
    } catch (error) {
        console.log(error.message);
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

        const userFound = await User.findOne({ email });
        if (!userFound) return res.status(404).json({ message: "User not found" });

        const passwordValid = await bcrypt.compare(password, userFound.password);
        if (!passwordValid) return res.status(401).json({ message: "Invalid Password" });

        const token = await createAccessToken({ id: userFound._id });
        res.cookie("token", token);

        const userDTO = new UserDTO(userFound);

        res.status(200).json(userDTO);
    } catch (error) {
        console.log(error.message);
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

        const userFound = await User.findById({ _id: userId });

        if (!userFound) return res.status(401).json({ message: "Unauthorized" });

        const userDTO = new UserDTO(userFound);

        res.status(200).json(userDTO);
    } catch (error) {
        console.log(error.message);
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
