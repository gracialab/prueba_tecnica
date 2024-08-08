#!/usr/bin/python
""" holds class books"""
from models.base_model import BaseModel, Base
from os import getenv
from sqlalchemy import Column, String, ForeignKey


class Book(BaseModel, Base):
    """Representation of book """
    __tablename__ = 'books'
    id = Column(String(60), primary_key=True)
    category_id = Column(String(60), ForeignKey('categories.id'), nullable=False)
    name = Column(String(128), nullable=False)
    file_name = Column(String(128), nullable=True)
