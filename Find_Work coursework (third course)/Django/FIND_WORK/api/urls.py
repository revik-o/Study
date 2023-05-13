from django.urls import path
from .views import LogIn, Table, Option, ExportExel

urlpatterns = [
    path('log-in/', LogIn.as_view()),
    path('table/', Table.as_view()),
    path('options/', Option.as_view()),
    path('exel/', ExportExel.as_view()),
    # path('EXPORT/<>')
]