#!/usr/bin/python3
""" holds class Category"""
import models
from models.base_model import BaseModel, Base
from models.book import Book
from os import getenv
from sqlalchemy import Column, String
from sqlalchemy.orm import relationship


class Category(BaseModel, Base):
    """Representation of Category """

    __tablename__ = 'categories'
    id = Column(String(60), primary_key=True)
    name = Column(String(128), nullable=False)
    description = Column(String(128), nullable=True)
    books= relationship("Book",
                            backref="categories",
                            cascade="all, delete, delete-orphan")

    @property
    def categories(self):
        """getter for list of books instances related to the category"""
        book_list = []
        all_categories = models.storage.all(Book)
        for book in all_categories.values():
            if book.category_id == self.id:
                book_list.append(book)
        return book_list
