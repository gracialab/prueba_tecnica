/**
 * Middleware function to validate request data against a provided schema using Zod.
 * 
 * This middleware function validates the request body against a schema defined by Zod. If the request body
 * is valid according to the schema, the request is passed to the next middleware or route handler. 
 * If the request body is invalid, a response with status 400 and a list of validation errors is sent to the client.
 * 
 * @param {ZodSchema} data - The Zod schema to validate the request body against. This schema should define the expected structure and constraints for the request data.
 * 
 * @returns {Function} A middleware function that takes `req`, `res`, and `next` as parameters.
 * 
 * @param {Object} req - The request object from the client. It contains the `body` property which is validated against the schema.
 * @param {Object} res - The response object used to send the response to the client. If validation fails, a 400 status and error messages are sent.
 * @param {Function} next - The next middleware function in the stack to call if the request body is valid.
 * 
 * @throws {Object} If the request body fails validation, it sends an HTTP response with status 400 and an array of error messages.
 * 
 * @example
 * // Example usage in an Express.js route
 * import { z } from "zod";
 * import { validateSchema } from "./middleware/validateSchema.js";
 * 
 * const bookSchema = z.object({
 *     name: z.string().nonempty(),
 *     author: z.string().nonempty(),
 *     publishedYear: z.number().int().positive()
 * });
 * 
 * app.post('/books', validateSchema(bookSchema), (req, res) => {
 *     // Handle the valid request
 *     res.send('Book added successfully');
 * });
 */
export const validateSchema = (data) => (req, res, next) => {
    try {
        data.parse(req.body);
        next();
    }
    catch (error) {
        const { issues } = error;
        const errors = issues.map(err => err.message);
        return res.status(400).json({ status: "error", message: errors });
    }
}