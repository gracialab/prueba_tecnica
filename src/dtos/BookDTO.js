export class BookDTO {
    constructor({ _id, userId, name, author, publishedYear }){
        this._id = _id;
        this.userId = userId,
        this.name = name,
        this.author = author,
        this.publishedYear = publishedYear
    }
}