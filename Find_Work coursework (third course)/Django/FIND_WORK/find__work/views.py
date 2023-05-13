from django.shortcuts import render, redirect
from django.conf import settings

from libs___.connect_DB import cursor as CONNECT
from libs___.cipher import cipher
from sql_queries.sql import Category_f_t_, Experience_f_t_, Place_f_t_, User_g_v_

SESSION_KEY_USER = 'email_user'

def FindWork_User(request):
    if SESSION_KEY_USER in request.session:
        cursor = CONNECT()
        cursor.execute('SELECT id, e_mail, photo_dir_ FROM public."user" WHERE e_mail = \'{0}\';'.format(
            request.session[SESSION_KEY_USER]
        ))
        try: 
            info_ = cursor.fetchall()[0]
        except:
            request.session.flush()
            return redirect('FindWork')
        ################# __init__FindWork()
        cursor.execute(Category_f_t_)
        category = cursor.fetchall()
        cursor.execute(Experience_f_t_)
        experience = cursor.fetchall()
        cursor.execute(Place_f_t_)
        place = cursor.fetchall()
        cursor.execute('SELECT Job.id, name_job, company_name, photo_dir_, name_category, name_place, ' +
            'name_experience, salary, Job.description, Job.date ' + 
            'FROM public.job Job ' +
            'INNER JOIN public.company Company ON Job.company_id = Company.id ' + 
            'INNER JOIN public.category Category ON Job.category_id = Category.id ' +
            'INNER JOIN public.place Place ON Job.place_id = Place.id ' + 
            'INNER JOIN public.experience Experience ON Job.experience_id = Experience.id;'
        )
        job = cursor.fetchall()
        #################################
        def img_():
            try:
                return settings.MEDIA_URL + info_[2]
            except: 
                return ''
        context = {
            'user_info' : {
                'id' : info_[0],
                'email' : info_[1],
                'img' : img_()
            },
            'category' : category,
            'experience' : experience,
            'place' : place,
            'job' : job
        }
        return render(request, 'find work/FindWork.html', context)
    elif request.method == 'POST':
        if 'email' in request.POST:
            email = request.POST['email']
            password = request.POST['password']
            cursor = CONNECT()
            cursor.execute(User_g_v_ + 'e_mail = \'{0}\' AND password = \'{1}\';'.format(
                email, cipher(password)
            ))
            try: value = cursor.fetchall()[0][0]
            except: value = ''
            if email == value:
                cursor.execute('SELECT id, e_mail, photo_dir_ FROM public."user" WHERE e_mail = \'{0}\';'.format(
                    email
                ))
                try:
                    info_ = cursor.fetchall()[0]
                except:
                    return redirect('user_LogIn')

                ################# __init__FindWork()
                cursor.execute(Category_f_t_)
                category = cursor.fetchall()
                cursor.execute(Experience_f_t_)
                experience = cursor.fetchall()
                cursor.execute(Place_f_t_)
                place = cursor.fetchall()
                cursor.execute('SELECT Job.id, name_job, company_name, photo_dir_, name_category, name_place, ' +
                    'name_experience, salary, Job.description, Job.date ' + 
                    'FROM public.job Job ' +
                    'INNER JOIN public.company Company ON Job.company_id = Company.id ' + 
                    'INNER JOIN public.category Category ON Job.category_id = Category.id ' +
                    'INNER JOIN public.place Place ON Job.place_id = Place.id ' + 
                    'INNER JOIN public.experience Experience ON Job.experience_id = Experience.id;'
                )
                job = cursor.fetchall()
                #################################
                def img_():
                    try:
                        return settings.MEDIA_URL + info_[2]
                    except: 
                        return ''
                context = {
                    'user_info' : {
                        'id' : info_[0],
                        'email' : info_[1],
                        'img' : img_()
                    },
                    'category' : category,
                    'experience' : experience,
                    'place' : place,
                    'job' : job
                }
                request.session[SESSION_KEY_USER] = email
                return render(request, 'find work/FindWork.html', context)
            else: return redirect('user_LogIn')
    cursor = CONNECT()
    ################################
    cursor.execute(Category_f_t_)
    category = cursor.fetchall()
    cursor.execute(Experience_f_t_)
    experience = cursor.fetchall()
    cursor.execute(Place_f_t_)
    place = cursor.fetchall()
    cursor.execute('SELECT Job.id, name_job, company_name, photo_dir_, name_category, name_place, ' +
        'name_experience, salary, Job.description, Job.date ' + 
        'FROM public.job Job ' +
        'INNER JOIN public.company Company ON Job.company_id = Company.id ' + 
        'INNER JOIN public.category Category ON Job.category_id = Category.id ' +
        'INNER JOIN public.place Place ON Job.place_id = Place.id ' + 
        'INNER JOIN public.experience Experience ON Job.experience_id = Experience.id;'
    )
    job = cursor.fetchall()
    ##########################################
    context = {
        'category' : category,
        'experience' : experience,
        'place' : place,
        'job' : job
    }
    return render(request, 'find work/FindWork.html', context)


def FindWork_Filter(request):
    if request.method == 'POST' and 'search' in request.POST:
        search = request.POST['search']
        category = request.POST['category']
        experiense = request.POST['experiense']
        place = request.POST['place']
        cursor = CONNECT()
        cursor.execute(
            'SELECT Job.id, name_job, company_name, photo_dir_, name_category, name_place, ' +
            'name_experience, salary, Job.description, Job.date FROM public.job Job ' +
            'INNER JOIN public.company Company ON Job.company_id = Company.id ' +
            'INNER JOIN public.category Category ON Job.category_id = Category.id ' +
            'INNER JOIN public.place Place ON Job.place_id = Place.id ' + 
            'INNER JOIN public.experience Experience ON Job.experience_id = Experience.id ' + 
            'WHERE name_job like \'{0}%\'  AND category_id = '.format(search) + 
            '(SELECT id FROM public.category WHERE name_category = \'{0}\') '.format(category) +
            'AND experience_id = ' +
            '(SELECT id FROM public.experience WHERE name_experience = \'{0}\')'.format(experiense) +
            'AND place_id = (SELECT id FROM public.place WHERE name_place = \'{0}\');'.format(place)
            # 'SELECT Job.id, name_job, company_name, photo_dir_, name_category, name_place, ' +
            # 'name_experience, salary, Job.description, Job.date ' + 
            # 'FROM public.job Job ' +
            # 'INNER JOIN public.company Company ON Job.company_id = Company.id ' + 
            # 'INNER JOIN public.category Category ON Job.category_id = Category.id ' +
            # 'INNER JOIN public.place Place ON Job.place_id = Place.id ' + 
            # 'INNER JOIN public.experience Experience ON Job.experience_id = Experience.id' + 
            # ' WHERE name_job like ' +
            # '\'{0}%\' AND category_id = \'{1}\' AND experience_id = \'{2}\' AND place_id = \'{3}\';'.format(
            #     search, category, experiense, place
            # )
        )
        try: job = cursor.fetchall()
        except: job = []

        ###########################
        cursor.execute(Category_f_t_)
        category_ = cursor.fetchall()
        cursor.execute(Experience_f_t_)
        experience_ = cursor.fetchall()
        cursor.execute(Place_f_t_)
        place_ = cursor.fetchall()
        ##########################
        context = {
            'category' : category_,
            'category_s' : category,
            'experience' : experience_,
            'experience_s' : experiense,
            'place' : place_,
            'place_s' : place,
            'search' : search,
            'job' : job
        }

        return render(request, 'find work/FindWorkFilter.html', context)