import mongoose from "mongoose";

export const connectDb = () => {
    try {
        mongoose.connect("mongodb://localhost:27017/prueba_techn");
        console.log("Db connected");
    }
    catch (error) {
        console.log("Error: ", error.message);
    }
}