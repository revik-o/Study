from django.urls import path
from .views import FindWork_User, FindWork_Filter

urlpatterns = [
    # path('Main/', index, name='FindWorkMain'),
    path('', FindWork_User, name='FindWork'),
    path('_/', FindWork_Filter, name='FindWork_'),
]