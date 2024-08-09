import mongoose from "mongoose";
const ObjectId = mongoose.Types.ObjectId;

export const bookModel = new mongoose.Schema({
    _id: {
        type: String,
        default: () => new ObjectId().toString()
    },
    userId: {
        type: String,
        ref: "User",
        content: String,
        required: true,
    },
    name: {
        type: String,
        trim: true,
        required: true, 
    },
    author: {
        type: String,
        trim: true,
        required: true,
    },
    publishedYear: {
        type: Number,
        required: true,
    },
}, {
    timestamps: true, 
});

export default mongoose.model("Book", bookModel);