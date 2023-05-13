from django.shortcuts import render, redirect
from django.core.files.storage import FileSystemStorage

from libs___.connect_DB import cursor as CONNECT
from libs___.cipher import cipher
from sql_queries.sql import Place_f_t_

SESSION_KEY_COMPANY = 'email_company'

# Create your views here.
def LogIn(request):
    return render(request, 'find people/company/LogIn.html')

def CheckIn(request):
    if request.method == 'POST':
        email = request.POST['email']
        password = request.POST['password']
        password_again = request.POST['password_again']
        if password == password_again and password != '':
            cursor = CONNECT()
            cursor.execute('INSERT INTO public.company(e_mail, password) VALUES (\'{0}\', \'{1}\');'.format(
                email, cipher(password)
            ))
            request.session[SESSION_KEY_COMPANY] = email
            return redirect('company_AddInfo')
        else:
            return render(request, 'find people/company/CheckIn.html', {'email':email})
    return render(request, 'find people/company/CheckIn.html')

def AddInfo(request):
    name = ''           # name_company
    description = ''    # description
    if request.method == 'POST' and 'photo' in request.FILES:
        photo = request.FILES['photo']
        PHOTO___DIR___ = 'FIND PEOPLE/' + request.session[SESSION_KEY_COMPANY] + '/' + photo.name
        FileSystemStorage().save(PHOTO___DIR___, photo)
        name = request.POST['name_company']
        description = request.POST['description']
        cursor = CONNECT()
        cursor.execute(
            'UPDATE public.company SET company_name=\'{0}\', description=\'{1}\', photo_dir_=\'{2}\' WHERE e_mail = \'{3}\';'.format(
                name, description, PHOTO___DIR___, request.session[SESSION_KEY_COMPANY]
            )
        )
        return redirect('company_LogIn')
    elif request.method == 'POST':
        name = request.POST['name_company']
        description = request.POST['description']
        cursor = CONNECT()
        cursor.execute(
            'UPDATE public.company SET company_name=\'{0}\', description=\'{1}\' WHERE e_mail = \'{2}\';'.format(
                name, description, request.session[SESSION_KEY_COMPANY]
            )
        )
        return redirect('company_LogIn')

    # request.session.flush()


    if SESSION_KEY_COMPANY in request.session:
        return render(request, 'find people/company/AddInfo.html', 
            {'email':request.session[SESSION_KEY_COMPANY]})
    else: return redirect('company_LogIn')