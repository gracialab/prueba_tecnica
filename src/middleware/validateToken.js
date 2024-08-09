import jwt from "jsonwebtoken";
import { TOKEN_SECRET } from "../config/config.js";

/**
 * Middleware function to check if a request has a valid JWT token.
 * 
 * This middleware function checks for the presence of a JWT token in the cookies of the incoming request. 
 * If a token is present, it verifies the token using the secret key. If the token is valid, the user ID 
 * extracted from the token is added to the request object. If the token is invalid or not present, 
 * appropriate HTTP status codes and messages are sent as responses.
 * 
 * @param {Object} req - The request object from the client. It contains the `cookies` property to check for the token and will be extended with the `userId` property if the token is valid.
 * @param {Object} res - The response object used to send the response to the client.
 * @param {Function} next - The next middleware function in the stack to call if the token is valid.
 * 
 * @returns {void} Calls `next()` if the token is valid, otherwise sends an HTTP response with status 401 or 403.
 * 
 * @throws {Object} If the token is missing or invalid, it sends an HTTP response with status 401 or 403 and a message indicating the error.
 * 
 * @example
 * // Example usage in an Express.js route
 * app.get('/protected-route', authRequired, (req, res) => {
 *     res.send('This is a protected route');
 * });
 */
export const authRequired = (req, res, next) => {
    const { token } = req.cookies;

    if (!token) return res.status(401).json({ message: "Unauthorized" });

    jwt.verify(token, TOKEN_SECRET, (error, decodedToken) => {
        if (error) return res.status(403).json({ message: 'Invalid Token' });
        
        req.userId = decodedToken.id;
        next();
    })
}