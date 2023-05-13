from django.urls import path
from .views import LogIn, CheckIn, AddInfo, LogOut, UpdateInfo, AddResume, resume

urlpatterns = [
    path('Log-In/', LogIn, name='user_LogIn'),
    path('Log-out/', LogOut, name='user_LogOut'),
    path('Check-In/', CheckIn, name='user_CheckIn'),
    path('UpdateInfo/', UpdateInfo, name='user_UpdateInfo'),
    path('AddResume/', AddResume, name='user_AddResume'),
    # path('UpdateInfo/resume/<slug:email>/<int:idRes>/', resume, name='user_Resume'),
    path('UpdateInfo/resume/<int:email>/<int:idRes>/', resume, name='user_Resume'),
    path('', AddInfo, name='user_AddInfo'),
]