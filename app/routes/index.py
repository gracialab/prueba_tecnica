#!/usr/bin/python3
""" objects that handle all default Categories"""
from models.category import Category
from models import storage
from app.routes import app_routes
from flask import abort, jsonify, make_response, request, render_template, redirect, url_for


@app_routes.route('/')
def login():
    return render_template("login.html")

@app_routes.route('/registro')
def register():
    return render_template('registro.html')

@app_routes.route('/allbooks')
def index():
    return render_template('allbooks.html')

@app_routes.route('/admin')
def get_categories():
    return render_template("admin.html")

@app_routes.route("/books")
def get_books():

    return render_template("books.html")