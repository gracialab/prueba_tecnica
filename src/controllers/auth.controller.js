import bcrypt from "bcryptjs";
import User from "../models/user.model.js";
import { createAccessToken } from "../lib/createToken.js";

export const registerUser = async (req, res) => {
    try {
        const { username, email, password } = req.body;
        const passwordHash = await bcrypt.hash(password, 12);

        const newUser = User({
            username,
            email,
            password: passwordHash,
        })

        const userSave = await newUser.save();

        const token = await createAccessToken({ id: userSave._id });
        res.cookie("token", token);

        res.status(200).json({
            username: userFound.username,
            email: userFound.email,
            createdAt: userFound.createdAt,
            updatedAt: userFound.updatedAt,
        });
    }
    catch (error) {
        console.log(error.message);
    }
}

export const loginUser = async (req, res) => {
    try {
        const { email, password } = req.body;

        const userFound = await User.findOne({ email });
        if (!userFound) return res.status(404).json({ message: "User not found" });

        const passwordValid = await bcrypt.compare(password, userFound.password);
        if (!passwordValid) return res.status(401).json({ message: "Invalid Password" });
        
        const token = await createAccessToken({ id: userFound._id });
        res.cookie("token", token);

        res.status(200).json({
            username: userFound.username,
            email: userFound.email,
            createdAt: userFound.createdAt,
            updatedAt: userFound.updatedAt,
        });
    }
    catch (error) {
        console.log(error.message);
    }
}