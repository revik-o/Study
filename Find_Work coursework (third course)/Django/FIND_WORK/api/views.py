from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from django.http import JsonResponse, HttpResponse

from libs___.connect_DB import cursor as CONNECT
from libs___.cipher import cipher

class LogIn(APIView):
    def post(self, request, *args, **kwargs):
        if request.data['login'] == 'admin':
            if request.data['password'] == 'admin':
                return JsonResponse({'answer' : 'ok'})
        return JsonResponse({'answer' : 'none'})

class Table(APIView):
    def post(self, request, *args, **kwargs):
        cursor = CONNECT()
        if request.data['query'] == 'SELECT':
            lines_table = []
            if request.data['table']=='category' or request.data['table']=='company' or request.data['table']=='experience' or request.data['table']=='place':
                cursor.execute('SELECT * FROM public."{0}";'.format(request.data['table']))
                if request.data['password exist'][0]:
                    temp_ = cursor.fetchall()
                    index = int(request.data['password exist'][1])
                    for line in temp_:
                        temp = []
                        for val in line:
                            if line.index(val) == index: temp.append('*****')
                            else: temp.append(val)
                        lines_table.append(temp)
                else: lines_table = cursor.fetchall()
            elif request.data['table'] == 'job':
                cursor.execute('SELECT * FROM public.job_view;')
                lines_table = cursor.fetchall()
            elif request.data['table'] == 'resume':
                cursor.execute('SELECT * FROM public.resume_view;')
                lines_table = cursor.fetchall()
            elif request.data['table'] == 'user':
                cursor.execute('SELECT * FROM public.user_view;')
                temp_ = cursor.fetchall()
                index = int(request.data['password exist'][1])
                for line in temp_:
                    temp = []
                    for val in line:
                        if line.index(val) == index: temp.append('*****')
                        else: temp.append(val)
                    lines_table.append(temp)
            return JsonResponse({'lines' : lines_table})
        elif request.data['query'] == 'INSERT':
            sql = 'INSERT INTO public."{0}"('.format(request.data['table'])
            key_column = request.data['key_column']
            info = request.data['info']
            str_temp = ''
            for index in range(1, len(key_column)):
                if info[index] != 'none':
                    if index == len(key_column) - 1: str_temp += '"{0}"'.format(key_column[index])
                    else: str_temp += '"{0}", '.format(key_column[index])
            if str_temp[len(str_temp)-2] == ',':
                for index in range(len(str_temp)-2): sql += str_temp[index]
                sql += ') VALUES ('
            else: sql += str_temp + ') VALUES ('
            str_temp = ''
            for index in range(1, len(info)):
                if info[index] != 'none':
                    if index == len(info) - 1: str_temp += '\'{0}\''.format(str(info[index]))
                    elif request.data['password exist'][0] and request.data['password exist'][1] == index: 
                        str_temp += '\'{0}\', '.format(cipher(info[index]))
                    else: str_temp += '\'{0}\', '.format(str(info[index]))
            if str_temp[len(str_temp)-2] == ',':
                for index in range(len(str_temp)-2): sql += str_temp[index]
                sql += ');'
            else: sql += str_temp + ');'
            cursor.execute(sql)
            return JsonResponse({'answer' : 'OK'})
        elif request.data['query'] == 'UPDATE':
            sql = 'UPDATE public."{0}" SET '.format(request.data['table'])
            key_column = request.data['key_column']
            info = request.data['info']
            str_temp = ''
            for index in range(1, len(key_column)):
                if info[index] != 'none':
                    if index == len(key_column) - 1: str_temp += '"{0}"=\'{1}\''.format(key_column[index], str(info[index]))
                    else: str_temp += '"{0}"=\'{1}\', '.format(key_column[index], str(info[index]))
            if str_temp[len(str_temp)-2] == ',':
                for index in range(len(str_temp)-2): sql += str_temp[index]
                sql += ' WHERE id = \'{0}\';'.format(str(request.data['value id']))
            else: sql += str_temp + ' WHERE id = \'{0}\';'.format(str(request.data['value id']))
            cursor.execute(sql)
            return JsonResponse({'answer' : 'OK'})
        elif request.data['query'] == 'DELETE':
            cursor.execute('DELETE FROM public."{0}" WHERE id = \'{1}\''.format(
                request.data['table name'], str(request.data['value id'])
            ))
            return JsonResponse({'answer' : 'OK'})

class Option(APIView):
    def post(self, request, *args, **kwargs):
        cursor, option = CONNECT(), []
        if request.data['word'] == 'company_id':
            cursor.execute('SELECT id, company_name FROM public.company;')
            option = cursor.fetchall()
        elif request.data['word'] == 'category_id':
            cursor.execute('SELECT * FROM public.category;')
            option = cursor.fetchall()
        elif request.data['word'] == 'place_id':
            cursor.execute('SELECT * FROM public.place;')
            option = cursor.fetchall()
        elif request.data['word'] == 'experience_id':
            cursor.execute('SELECT * FROM public.experience;')
            option = cursor.fetchall()
        elif request.data['word'] == 'user_id':
            cursor.execute('SELECT id, e_mail, full_name FROM public."user";')
            option = cursor.fetchall()
        return JsonResponse({'option' : option})

import xlwt

class ExportExel(APIView):
    def post(self, request, *args, **kwargs):
        cursor = CONNECT()
        name_table = request.data['table']
        book = xlwt.Workbook()
        sheet = book.add_sheet(name_table)
        y = 0
        if name_table=='category' or name_table=='company' or name_table=='experience' or name_table=='place':
            cursor.execute('SELECT * FROM public."{0}";'.format(name_table))
        elif name_table == 'job':
            cursor.execute('SELECT * FROM public.job_view;')
        elif name_table == 'resume':
            cursor.execute('SELECT * FROM public.resume_view;')
        elif name_table == 'user':
            cursor.execute('SELECT * FROM public.user_view;')
        for line in cursor.fetchall():
            x = 0
            for val in line:
                try: sheet.col(x).width = 256 * (len(val) + 1)
                except: sheet.col(x).width = 256 * 4
                sheet.write(y, x, val)
                x += 1
            y += 1
        from io import BytesIO
        File = BytesIO()
        book.save(File)
        import base64
        bytes_base64 = base64.b64encode(File.getvalue())
        str_bytes = str(bytes_base64)
        TEMP = ''
        for i in range(2, len(str_bytes) - 1):
            TEMP += str_bytes[i]
        return JsonResponse({'contentType' : 'application/vnd.ms-excel', 'base64' : TEMP})