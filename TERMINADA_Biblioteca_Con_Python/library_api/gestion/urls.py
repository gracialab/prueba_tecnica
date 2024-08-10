from django.urls import path
from .views import (
    ApiRootView,  # Asegúrate de importar la nueva vista
    BookListView,
    BookDetailView,
    BookCreateView,
    BookUpdateView,
    BookDeleteView,
    UserCreateView 
)
urlpatterns = [
    path('', ApiRootView.as_view(), name='api-root'), 
    path('user/create/', UserCreateView.as_view(), name='user-create'),
    path('books/', BookListView.as_view(), name='book-list'),
    path('books/create/', BookCreateView.as_view(), name='book-create'),
    path('books/<int:pk>/', BookDetailView.as_view(), name='book-detail'),
    path('books/<int:pk>/update/', BookUpdateView.as_view(), name='book-update'),
    path('books/<int:pk>/delete/', BookDeleteView.as_view(), name='book-delete'),
]