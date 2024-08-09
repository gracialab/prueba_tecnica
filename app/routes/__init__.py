#!/usr/bin/python3
""" Blueprint for API """
from flask import Blueprint

app_routes = Blueprint('app_routes', __name__, url_prefix='/glab_books', template_folder='templates/', static_folder='static')

from app.routes.index import *


