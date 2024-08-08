#!/usr/bin/python3
""" Flask Application """
from models import storage
from models.user import User
from app.routes import app_routes
from flask import Flask, jsonify


app = Flask(__name__)
app.register_blueprint(app_routes)


if __name__ == "__main__":
    """ Main Function """
    app.run(host='0.0.0.0', port=5000, debug=True)
