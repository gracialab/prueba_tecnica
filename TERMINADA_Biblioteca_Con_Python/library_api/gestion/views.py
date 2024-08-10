from django.shortcuts import render, redirect
from django.views import View
from .models import Book
from .forms import BookForm
from django.urls import reverse_lazy
from django.views.generic.edit import CreateView
from django.contrib.auth.models import User



class HomeView(View):
    def get(self, request, *args, **kwargs):
        return render(request, 'home.html')

class UserCreateView(CreateView):
    model = User
    fields = ['username', 'password']  
    template_name = 'user_create.html'
    success_url = reverse_lazy('user-list')  

class ApiRootView(View):
    def get(self, request, *args, **kwargs):
        return render(request, 'api_root.html')
        
class BookListView(View):
    def get(self, request, *args, **kwargs):
        books = Book.objects.all()
        return render(request, 'book_list.html', {'books': books})

class BookDetailView(View):
    def get(self, request, *args, **kwargs):
        book = Book.objects.get(pk=kwargs['pk'])
        return render(request, 'book_detail.html', {'book': book})

class BookCreateView(View):
    def get(self, request, *args, **kwargs):
        form = BookForm()
        return render(request, 'book_form.html', {'form': form})

    def post(self, request, *args, **kwargs):
        form = BookForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('book-list')
        return render(request, 'book_form.html', {'form': form})

class BookUpdateView(View):
    def get(self, request, *args, **kwargs):
        book = Book.objects.get(pk=kwargs['pk'])
        form = BookForm(instance=book)
        return render(request, 'book_form.html', {'form': form})

    def post(self, request, *args, **kwargs):
        book = Book.objects.get(pk=kwargs['pk'])
        form = BookForm(request.POST, instance=book)
        if form.is_valid():
            form.save()
            return redirect('book-list')
        return render(request, 'book_form.html', {'form': form})

class BookDeleteView(View):
    def get(self, request, *args, **kwargs):
        book = Book.objects.get(pk=kwargs['pk'])
        return render(request, 'book_confirm_delete.html', {'book': book})

    def post(self, request, *args, **kwargs):
        book = Book.objects.get(pk=kwargs['pk'])
        book.delete()
        return redirect('book-list')