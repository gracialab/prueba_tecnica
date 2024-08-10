from django.test import TestCase
from rest_framework.test import APIClient
from rest_framework import status
from .models import User, Book

class UserTests(TestCase):
    def setUp(self):
        self.client = APIClient()
        self.user = User.objects.create(username='testuser', password='password')
    
    def test_create_user(self):
        response = self.client.post('/api/users/', {'username': 'newuser', 'password': 'newpassword'})
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)

