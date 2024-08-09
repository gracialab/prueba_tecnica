import { z } from "zod";

export const userRegisterSchema = z.object({
    username: z.string()
        .min(1, { message: 'Name is required' })
        .regex(/^[a-zA-Z\s]+$/, { message: 'The name can only contain letters.' })
        .trim(),
    email: z.string()
        .min(1, { message: 'Email is required' })
        .trim()
        .email({ message: 'Invalid email' }),
    password: z.string()
        .min(8, { message: 'Password must be at least 8 characters' })
        .max(16, { message: 'Password must be at most 16 characters' })
        .regex(/^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])\S+$/, { 
            message: 'Password must include at least one digit, one lowercase letter, and one uppercase letter.'
        })
});

export const userLoginSchema = z.object({
    email: z.string()
        .min(1, { message: 'Email is required' })
        .trim()
        .email({ message: 'Invalid email' }),
    password: z.string()
        .min(8, { message: 'Password must be at least 8 characters' })
});
