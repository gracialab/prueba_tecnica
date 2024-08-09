import Router from "express";
import { loginUser, registerUser, profile, logout } from "../controllers/auth.controller.js";
import { authRequired } from "../middleware/validateToken.js";
import { validateSchema } from "../middleware/validateSchema.js";
import { userLoginSchema, userRegisterSchema } from "../schemas/user.schema.js";

const router = Router();

router.post("/register", validateSchema(userRegisterSchema), registerUser);
router.post("/login", validateSchema(userLoginSchema), loginUser);
router.post("/logout", logout);
router.get("/profile", authRequired, profile);

export default router;