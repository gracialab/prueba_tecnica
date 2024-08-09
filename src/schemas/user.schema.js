import { z } from "zod";

export const userRegisterSchema = z.object({
    username: z.string({
        required_error:  'username is required'
    })
    .regex(/^[a-zA-Z\s]+$/, { message: 'The name can only contain letters.' })
    .trim(),
    email: z.string({
        required_error:  'Email is required'
    })
    .trim()
    .email({
        required_error: 'Invalid Email'
    }),
    password: z.string({
        required_error: 'Password is required'
    })
    .regex(/^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$/, {
        message: 'Password requirements include length between 8 to 16 characters, with at least one digit, one lowercase letter, and one uppercase letter.'
    })
});

export const userLoginSchema = z.object({
    email: z.string({
        required_error: 'Email is required'
    })
    .trim()
    .email({
        required_error: 'Invalid Email'
    }),
    password: z.string({
        required_error: 'Password is required'
    })
    .regex(/^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$/, {
        message: 'Password requirements include length between 8 to 16 characters, with at least one digit, one lowercase letter, and one uppercase letter.'
    })
})
