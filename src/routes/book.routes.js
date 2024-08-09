import { Router } from "express";
import { authRequired } from "../middleware/validateToken.js";
import { createBook, deleteBook, getAllBooks, getBookById, updateBook } from "../controllers/book.controller.js";
import { validateSchema } from "../middleware/validateSchema.js";
import { bookValidationSchema } from "../schemas/book.schema.js";

const router = Router();

router.get("/", authRequired, getAllBooks);
router.get("/:id", authRequired, getBookById);
router.post("/", authRequired, validateSchema(bookValidationSchema), createBook);
router.put("/:id", authRequired, validateSchema(bookValidationSchema), updateBook);
router.delete("/:id", authRequired, deleteBook);

export default router;