import express from "express";
import morgan from "morgan";
import authRouter from "../routes/auth.route.js";
import bookRouter from "../routes/book.routes.js";
import cookieParser from "cookie-parser";

const app = express();

app.use(morgan("dev"));
app.use(express.json());
app.use(cookieParser());
app.use("/api/auth", authRouter);
app.use("/api/books", bookRouter);

export default app;