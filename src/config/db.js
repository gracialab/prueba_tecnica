import mongoose from "mongoose";

/**
 * Connects to the MongoDB database.
 * @function connectDb
 * @description This function attempts to connect to a MongoDB database using Mongoose.
 * It logs a success message if the connection is established, and logs an error message if the connection fails.
 * @throws {Error} Throws an error if the database connection fails.
 * @example
 * // Example usage
 * connectDb();
 */
export const connectDb = () => {
    try {
        // Connect to MongoDB using Mongoose
        mongoose.connect(process.env.MONGO_URI);
        console.log("Db connected");
    }
    catch (error) {
        // Log any errors that occur during connection
        console.log("Error: ", error.message);
    }
}
