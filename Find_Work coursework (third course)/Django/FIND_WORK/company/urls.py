from django.urls import path
from .views import LogIn, CheckIn, AddInfo

urlpatterns = [
    path('Log-In/', LogIn, name='company_LogIn'),
    path('Check-In/', CheckIn, name='company_CheckIn'),
    path('', AddInfo, name='company_AddInfo'),
]