import Router from "express";
import { loginUser, registerUser, profile, logout } from "../controllers/auth.controller.js";
import { authRequired } from "../middleware/validateToken.js";

const router = Router();

router.post("/register", registerUser);
router.post("/login", loginUser);
router.post("/logout", logout);
router.get("/profile", authRequired, profile);

export default router;