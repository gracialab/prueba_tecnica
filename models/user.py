#!/usr/bin/python3
""" holds class User"""

from models.base_model import Base, BaseModel
from os import getenv
from sqlalchemy import Column, String, ForeignKey, Boolean
from sqlalchemy.orm import relationship
from hashlib import md5
from flask import current_app
from werkzeug.security import generate_password_hash, check_password_hash


class User(BaseModel, Base):
    """Representation of a user """

    __tablename__ = 'users'
    id = Column(String(60), primary_key=True)
    email = Column(String(128), nullable=False)
    password_hash = Column(String(128), nullable=False)
    first_name = Column(String(128), nullable=True)
    last_name = Column(String(128), nullable=True)
    is_user = Column(Boolean, default=True)


    @property
    def password(self):
        raise AttributeError('password is not a readable attribute')


    def verify_password(self, password):
        return check_password_hash(self.password_hash, password)

    def set_password(self, password: str) -> None:
        self.password_hash = generate_password_hash(password)

    def __setattr__(self, name, value):
        """sets a password with md5 encryption"""
        if name == "password":
            value = md5(value.encode()).hexdigest()
        super().__setattr__(name, value)
