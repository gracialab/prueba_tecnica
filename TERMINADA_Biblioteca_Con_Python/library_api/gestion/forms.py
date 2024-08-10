# gestion/forms.py
from django import forms
from .models import Book

class BookForm(forms.ModelForm):
    class Meta:
        model = Book
        fields = ['title', 'author', 'published_date', 'isbn', 'pages', 'cover', 'language']
        widgets = {
            'published_date': forms.DateInput(attrs={'type': 'date'}),
            'cover': forms.URLInput(attrs={'placeholder': 'http://example.com/cover.jpg'}),
            'language': forms.TextInput(attrs={'placeholder': 'e.g., English'}),
        }
