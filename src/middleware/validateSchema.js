export const validateSchema = (data) => (req, res, next) => {
    try {
        data.parse(req.body);
        console.log(data.parse(req.body));
        next();
    }
    catch (error) {
        const { issues } = error;
        const errors = issues.map(err => err.message);
        return res.status(400).json({ status: "error", message: errors });
    }
}