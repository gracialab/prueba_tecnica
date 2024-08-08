#!/usr/bin/python3
""" objects that handle all default RestFul API actions for Users """
from models.user import User
from models import storage
from api.v1.views import app_views
from flask import abort, jsonify, make_response, request
from flasgger.utils import swag_from
from flask_cors import CORS, cross_origin
from flask_jwt_extended import (create_access_token, get_jwt_identity, unset_jwt_cookies)

@app_views.route('/users', methods=['GET'], strict_slashes=False)
@swag_from('documentation/user/all_users.yml')
def get_users():
    """
    Retrieves the list of all user objects
    or a specific user
    """
    all_users = storage.all(User).values()
    list_users = []
    for user in all_users:
        list_users.append(user.to_dict())
    return jsonify(list_users)


@app_views.route('/users/<user_id>', methods=['GET'], strict_slashes=False)
@swag_from('documentation/user/get_user.yml', methods=['GET'])
def get_user(user_id):
    """ Retrieves an user """
    user = storage.get(User, user_id)
    if not user:
        abort(404)

    return jsonify(user.to_dict())


@app_views.route('/users/<user_id>', methods=['DELETE'],
                 strict_slashes=False)
@swag_from('documentation/user/delete_user.yml', methods=['DELETE'])
def delete_user(user_id):
    """
    Deletes a user Object
    """

    user = storage.get(User, user_id)

    if not user:
        abort(404)

    storage.delete(user)
    storage.save()

    return make_response(jsonify({}), 200)



@app_views.route('/users/register', methods=['POST'], strict_slashes=False)
@swag_from('documentation/user/post_user.yml', methods=['POST'])
def register_user():
    data = request.get_json()
    if not data or not data.get('email') or not data.get('password'):
        return jsonify({'error': 'missing data'}), 400
    
    email = data['email']
    password = data['password']
    first_name = data.get('first_name')
    last_name = data.get('last_name')
    is_user = data.get('is_user', True)

    # Crear nuevo usuario
    user = User(email=email)
    user = User(email=email, first_name=first_name, last_name=last_name, is_user=is_user)
    user.set_password(password)
    user.save()

    # Generar token de acceso y responder
    access_token = create_access_token(identity=user.id)
    return jsonify({'message': 'user created', 'access_token': access_token}), 201

@app_views.route('/users/<user_id>', methods=['PUT'], strict_slashes=False)

@swag_from('documentation/user/put_user.yml', methods=['PUT'])
def put_user(user_id):
    """
    Updates a user
    """
    user = storage.get(User, user_id)

    if not user:
        abort(404)

    if not request.get_json():
        abort(400, description="Not a JSON")

    ignore = ['id', 'email', 'created_at', 'updated_at']

    data = request.get_json()
    for key, value in data.items():
        if key not in ignore:
            setattr(user, key, value)
    storage.save()
    return make_response(jsonify(user.to_dict()), 200)


@app_views.route('/users/login', methods=['POST'], strict_slashes=False)
@cross_origin()
@swag_from('documentation/user/login_user.yml', methods=['POST'])    
def login():
    data = request.get_json()
    if not data or not data.get('email') or not data.get('password'):
        return jsonify({'error': 'missing data'}), 400

    email = data['email']
    password = data['password']
    user = storage.get_user_by_email(email, password)
    if not user or not user.verify_password(password):
        return jsonify({'error': 'invalid credentials'}), 401

    access_token = create_access_token(identity=user.id)
    if user.is_user:
        return jsonify({'message': 'user authenticated', 'is_user': True, 'access_token': access_token}), 200
    else:
        return jsonify({'message': 'user authenticated', 'is_user': False, 'access_token': access_token}), 200


@app_views.route('/protected', methods=['GET'])
@swag_from('documentation/user/protected.yml', methods=['GET'])    
def protected():
    current_user_id = get_jwt_identity()
    user = storage.get(User, "id", current_user_id)
    if user.is_user:
        return jsonify({'message': 'Welcome to the user area!'}), 200
    else:
        return jsonify({'message': 'Welcome to the admin'})


@app_views.route('/logout', methods=['POST'])
@swag_from('documentation/user/logout.yml', methods=['POST'])   
def logout():
    resp = jsonify({'logout': True})
    unset_jwt_cookies(resp)
    return make_response(resp, 200)
