from django.db import connections

def cursor(db_name = 'postgres_____'):
    cursor_ = connections[db_name].cursor()
    return cursor_