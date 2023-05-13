# _f_t_ = full table

Place_f_t_ = 'SELECT name_place FROM public.place;'

Experience_f_t_ = 'SELECT name_experience FROM public.experience;'

Category_f_t_ = 'SELECT name_category FROM public.category;'

# _g_v_ = get value

Company_g_v_ = 'SELECT e_mail FROM public.company WHERE ' #'';'

User_g_v_ = 'SELECT e_mail FROM public."user" WHERE ' #'';'


"""
SELECT Job.id, name_job, company_name, photo_dir_, name_category, name_place, 
	name_experience, salary, Job.description, Job.date
	FROM public.job Job
	INNER JOIN public.company Company ON Job.company_id = Company.id
	INNER JOIN public.category Category ON Job.category_id = Category.id
	INNER JOIN public.place Place ON Job.place_id = Place.id
	INNER JOIN public.experience Experience ON Job.experience_id = Experience.id;
"""

"""
SELECT public.resume.id, name_category , full_name, phone, photo_dir_, name_resume, skills, 
	experience, languages, description, find_me
	FROM public.resume
	INNER JOIN public.category ON category_id = public.category.id
	INNER JOIN public."user" ON user_id = public."user".id
"""