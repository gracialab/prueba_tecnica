import app from "./config/app.js";
import { connectDb } from "./config/db.js";
connectDb();
app.listen(3000);

console.log("Server list on Port", 3000);