from django.shortcuts import render, redirect
from django.core.files.storage import FileSystemStorage
from django.http import HttpResponse
from django.conf import settings
from django.template.loader import get_template

from libs___.connect_DB import cursor as CONNECT
from libs___.cipher import cipher
from sql_queries.sql import Place_f_t_, User_g_v_, Category_f_t_

SESSION_KEY_USER = 'email_user'

# Create your views here.
def resume(request, email, idRes):
    cursor = CONNECT()
    cursor.execute(
        'SELECT public.resume.id, name_category , full_name, phone, photo_dir_, name_resume, skills, '+
        'experience, languages, description, find_me FROM public.resume INNER JOIN'+
        ' public.category ON category_id = public.category.id '+
        'INNER JOIN public."user" ON user_id = public."user".id '+
        'WHERE user_id = {0} AND public.resume.id = {1};'.format(
            email, idRes
        )
    )
    INFO = cursor.fetchall()[0]
    User_Name = INFO[2]
    Name_Resume = INFO[5]
    Phone = INFO[3]
    Skills = INFO[6]
    Experience = INFO[7]
    Languages = INFO[8]
    Description = INFO[9]
    Find_Me = INFO[10]

    import base64

    f = open(settings.MEDIA_ROOT + '/' + INFO[4], 'rb')
    bytes_base64 = base64.b64encode(f.read())

    str_bytes = str(bytes_base64)
    TEMP = ''

    for i in range(2, len(str_bytes) - 1):
        TEMP += str_bytes[i]


    URL_Img = TEMP

    context = {
        'User_Name' : User_Name,
        'Name_Resume' : Name_Resume,
        'Phone' : Phone,
        'URL_Img' : URL_Img,
        'Skills' : Skills,
        'Experience' : Experience,
        'Languages' : Languages,
        'Description' : Description,
        'Find_Me' : Find_Me
    }

    from io import BytesIO
    template_ = get_template('find work/user/PDF.html')
    html = template_.render(context)
    import weasyprint
    PDF = weasyprint.HTML(BytesIO(html.encode())).write_pdf()
    return HttpResponse(PDF, content_type='application/pdf')


def LogIn(request):
    if request.method == 'POST':
        email = request.POST['email']
        password = request.POST['password']
        cursor = CONNECT()
        print(cipher(password))
        cursor.execute(User_g_v_ + 'e_mail = \'{0}\' AND password = \'{1}\';'.format(
            email, cipher(password)
        ))
        if email == cursor.fetchall()[0][0]:
            request.session[SESSION_KEY_USER] = email
            return redirect('user_CheckIn')#
        else:
            return render(request, 'find work/user/LogIn.html', {'enter_str':'error'})
    return render(request, 'find work/user/LogIn.html')

def CheckIn(request):
    if request.method == 'POST':
        email = request.POST['email']
        password = request.POST['password']
        password_again = request.POST['password_again']
        if password == password_again and password != '':
            cursor = CONNECT()
            print(cipher(password))
            cursor.execute('INSERT INTO public."user"(e_mail, password) VALUES (\''+ 
                email +'\', \''+ cipher(password) +'\');')
            request.session[SESSION_KEY_USER] = email
            return redirect('user_AddInfo')
        else:
            return render(request, 'find work/user/CheckIn.html', {'email':email})
    return render(request, 'find work/user/CheckIn.html')

def AddInfo(request):
    name = ''   # full_name
    phone = ''  # phone
    place = ''  # place
    if request.method == 'POST' and 'photo' in request.FILES:
        photo = request.FILES['photo']
        PHOTO___DIR___ = 'FIND WORK/' + request.session[SESSION_KEY_USER] + '/' + photo.name
        FileSystemStorage().save(PHOTO___DIR___, photo)
        name = request.POST['full_name']
        phone = request.POST['phone']
        place = request.POST['place']
        cursor = CONNECT()
        cursor.execute('UPDATE public."user" SET full_name=\''+ 
            name +'\', phone=\''+ phone +'\', photo_dir_=\''+ PHOTO___DIR___ +
            '\', place_id=(SELECT id FROM public.place WHERE name_place = \''+ str(place) +'\') WHERE e_mail = \''+ request.session[SESSION_KEY_USER] +'\';')
        request.session.flush()
        return redirect('user_LogIn')
    elif request.method == 'POST':
        name = request.POST['full_name']
        phone = request.POST['phone']
        place = request.POST['place']
        cursor = CONNECT()
        cursor.execute('UPDATE public."user" SET full_name=\''+ 
            name +'\', phone=\''+ phone +'\', place_id=(SELECT id FROM public.place WHERE name_place = \''
            + str(place) +'\') WHERE e_mail = \''+ request.session[SESSION_KEY_USER] +'\';')
        request.session.flush()
        return redirect('user_LogIn')    
    
    if SESSION_KEY_USER in request.session:
        email = request.session[SESSION_KEY_USER]
        cursor = CONNECT()
        cursor.execute(Place_f_t_)
        places = cursor.fetchall()
        context = {
            'email' : email,
            'places' : places
        }
        return render(request, 'find work/user/AddInfo.html', context)
    else: return redirect('user_LogIn')

def LogOut(request):
    request.session.flush()
    return redirect('user_AddInfo')

def UpdateInfo(request):
    name = ''   # full_name
    phone = ''  # phone
    place = ''  # place
    if request.method == 'POST' and 'photo' in request.FILES:
        photo = request.FILES['photo']
        PHOTO___DIR___ = 'FIND WORK/' + request.session[SESSION_KEY_USER] + '/' + photo.name
        FileSystemStorage().save(PHOTO___DIR___, photo)
        name = request.POST['full_name']
        phone = request.POST['phone']
        place = request.POST['place']
        cursor = CONNECT()
        cursor.execute('UPDATE public."user" SET full_name=\''+ 
            name +'\', phone=\''+ phone +'\', photo_dir_=\''+ PHOTO___DIR___ +
            '\', place_id=(SELECT id FROM public.place WHERE name_place = \''+ str(place) +'\') WHERE e_mail = \''+ request.session[SESSION_KEY_USER] +'\';')
        request.session.flush()
        return redirect('user_LogIn')
    elif request.method == 'POST':
        name = request.POST['full_name']
        phone = request.POST['phone']
        place = request.POST['place']
        cursor = CONNECT()
        # cursor.execute('UPDATE public."user" SET full_name=\''+ 
        #     name +'\', phone=\''+ phone +'\', place_id=\''+ 
        #     str(place) +'\' WHERE e_mail = \''+ request.session[SESSION_KEY_USER] +'\';')
        cursor.execute(
            'CALL public.user_info_update(' +
            '\'{0}\', \'{1}\', \'{2}\', \'{3}\');'.format(
                name, phone, place, request.session[SESSION_KEY_USER]
            ) 
        )
        request.session.flush()
        return redirect('user_LogIn')
    
    
    if SESSION_KEY_USER in request.session:
        email = request.session[SESSION_KEY_USER]
        cursor = CONNECT()
        cursor.execute(Place_f_t_)
        places = cursor.fetchall()
        cursor.execute('SELECT full_name, phone, place_id' +
	            ' FROM public."user" ' +
	            'WHERE e_mail = \''+ email +'\';')
        info_ = cursor.fetchall()[0]

        cursor.execute('SELECT id FROM public."user" WHERE e_mail = \'{0}\''.format(
            request.session[SESSION_KEY_USER]
        ))
        user_id = cursor.fetchall()[0][0]
        cursor.execute('SELECT id, category_id, user_id, name_resume FROM public.resume WHERE user_id = {0};'.format(
            user_id
        ))
        resumes = cursor.fetchall()
        cursor.execute('SELECT name_place FROM public.place WHERE id=(SELECT place_id FROM public."user" WHERE id = '+str(user_id)+');')
        sel_place = cursor.fetchall()[0][0]
        context = {
            'full_name' : info_[0],
            'phone' : info_[1],
            'place_s' : info_[2],
            'email' : email,
            'places' : places,
            'sel_place' : sel_place,
            'resumes' : resumes
        }
        return render(request, 'find work/user/INFO_USER.html', context)
    else: return redirect('user_LogIn')

def AddResume(request):
    if request.method == 'POST':
        category = request.POST['category']
        name_resume = request.POST['name_resume']
        skills = request.POST['skills']
        experience = request.POST['experience']
        languages = request.POST['languages']
        description = request.POST['description']
        find_me = request.POST['find_me']

        cursor = CONNECT()
        cursor.execute('SELECT id FROM public."user" WHERE e_mail = \'{0}\''.format(
            request.session[SESSION_KEY_USER]
        ))
        user_id = cursor.fetchall()[0][0]

        cursor.execute(
            'INSERT INTO public.resume(category_id, user_id, name_resume, skills, experience, ' +
            'languages, description, find_me) VALUES (' + 
            '(SELECT id FROM public.category WHERE name_category = \'{0}\'), \'{1}\', \'{2}\', \'{3}\', \'{4}\', \'{5}\', \'{6}\', \'{7}\');'.format(
                category, user_id, name_resume, skills, experience, languages, description, find_me
            )
        )

        return redirect('FindWork')


    if SESSION_KEY_USER in request.session:
        email = request.session[SESSION_KEY_USER]
        cursor = CONNECT()
        cursor.execute(Category_f_t_)
        category = cursor.fetchall()
        context = {
            'email' : email,
            'category' : category
        }
        return render(request, 'find work/user/AddResume.html', context)
