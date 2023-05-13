export const Table = {
    'FindWork' : {
        'category' : 'Категорія',
        'company' : 'Компанія',
        'experience' : 'Досвід',
        'job' : 'Вакансія',
        'place' : 'Місто',
        'resume' : 'Резюме',
        'user' : 'Користувач'
    }
}

export const Column = {
    'FindWork' : {
        'category' : {
            'id' : 'id',
            'name_category' : 'Назва категорії'
        },
        'company' : {
            'id' : 'id',
            'e_mail' : 'E-mail',
            'password' : 'Пароль',
            'company_name' : 'Назва компанії',
            'description' : 'Опис',
            'photo_dir_' : 'Фото'
        },
        'experience' : {
            'id' : 'id',
            'name_experience' : 'Досвід'
        },
        'job' : {
            'id' : 'id',
            'name_job' : 'Назва вакансії',
            'company_id' : 'Компанія',
            'category_id' : 'Категорія',
            'place_id' : 'Місто',
            'experience_id' : 'Досвід',
            'salary' : 'Заробітна плата',
            'description' : 'Опис',
            'date' : 'Дата'
        },
        'place' : {
            'id' : 'id',
            'name_place' : 'Назва Міста'
        },
        'resume' : {
            'id' : 'id',
            'category_id' : 'Категорія',
            'user_id' : 'Користувач',
            'name_resume' : 'Назва резюме',
            'skills' : 'Навички',
            'experience' : 'Досвід',
            'languages' : 'Мови',
            'description' : 'Опис',
            'find_me' : 'Про себе'
        },
        'user' : {
            'id' : 'id',
            'e_mail' : 'E-mail',
            'password' : 'Пароль',
            'full_name' : 'Повне ім\'я',
            'phone' : 'Телефон',
            'photo_dir_' : 'Фото',
            'place_id' : 'Місто'
        }
    }
}

export const Data_Base = {
    'FindWork' : 'Find Work'
}