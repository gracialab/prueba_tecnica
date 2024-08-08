import mongoose from "mongoose";
const ObjectId = mongoose.Types.ObjectId;

const userModel = new mongoose.Schema({
    _id: {
        type: String,
        default: () => new ObjectId().toString()
    },
    username: {
        type: String,
        trim: true,
        required: true,
    },
    email: {
        type: String,
        trim: true,
        required: true,
    },
    password: {
        type: String,
        trim: true,
        required: true,
    },
}, {
    timestamps: true,
})

export default mongoose.model("User", userModel);