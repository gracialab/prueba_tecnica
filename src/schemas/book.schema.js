import { z } from "zod";

export const bookValidationSchema = z.object({
    name: z.string({
        required_error: "Name is required.",
        invalid_type_error: "The name can only contain letters."
    })
        .trim(),
    author: z.string({
        required_error: "Author is required.",
        invalid_type_error: "The author can only contain letters."
    }).trim(),
    publishedYear: z.number({
        required_error: "Published Year is required."
    })
        .int({ message: "Published year must be an integer." })
        .positive({ message: "Published year must be a positive number." })
        .gte(1000, { message: "Published year must be a valid year." })
        .lte(new Date().getFullYear(), { message: "Published year cannot be in the future." }),
});
