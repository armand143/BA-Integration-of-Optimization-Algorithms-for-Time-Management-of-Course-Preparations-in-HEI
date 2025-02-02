# Generated by Django 4.1.5 on 2023-03-29 18:53

import django.contrib.auth.models
import django.contrib.auth.validators
from django.db import migrations, models
import django.db.models.deletion
import django.utils.timezone


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('auth', '0012_alter_user_first_name_max_length'),
    ]

    operations = [
        migrations.CreateModel(
            name='course',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('course_name', models.CharField(max_length=250)),
                ('courseNum', models.IntegerField(default=0)),
                ('course_credits', models.IntegerField()),
            ],
        ),
        migrations.CreateModel(
            name='courseContent',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('CourseComplexity', models.DecimalField(decimal_places=2, max_digits=2)),
                ('CourseFamiliarity', models.DecimalField(decimal_places=2, max_digits=2)),
            ],
        ),
        migrations.CreateModel(
            name='courseDidactic',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('CourseComplexity', models.DecimalField(decimal_places=2, max_digits=2)),
                ('CourseFamiliarity', models.DecimalField(decimal_places=2, max_digits=2)),
            ],
        ),
        migrations.CreateModel(
            name='courseImpact',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('d', models.DecimalField(decimal_places=2, max_digits=2)),
            ],
        ),
        migrations.CreateModel(
            name='courseLectTime',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('d', models.DecimalField(decimal_places=2, max_digits=2)),
            ],
        ),
        migrations.CreateModel(
            name='coursePresentation',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('finished', models.DecimalField(decimal_places=2, max_digits=2)),
                ('time0', models.DecimalField(decimal_places=2, max_digits=2)),
                ('pres0', models.DecimalField(decimal_places=2, max_digits=2)),
                ('complexity', models.DecimalField(decimal_places=2, max_digits=2)),
            ],
        ),
        migrations.CreateModel(
            name='courseStudentCount',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('students', models.IntegerField()),
            ],
        ),
        migrations.CreateModel(
            name='CustomUser',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('password', models.CharField(max_length=128, verbose_name='password')),
                ('last_login', models.DateTimeField(blank=True, null=True, verbose_name='last login')),
                ('is_superuser', models.BooleanField(default=False, help_text='Designates that this user has all permissions without explicitly assigning them.', verbose_name='superuser status')),
                ('username', models.CharField(error_messages={'unique': 'A user with that username already exists.'}, help_text='Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only.', max_length=150, unique=True, validators=[django.contrib.auth.validators.UnicodeUsernameValidator()], verbose_name='username')),
                ('first_name', models.CharField(blank=True, max_length=150, verbose_name='first name')),
                ('last_name', models.CharField(blank=True, max_length=150, verbose_name='last name')),
                ('email', models.EmailField(blank=True, max_length=254, verbose_name='email address')),
                ('is_staff', models.BooleanField(default=False, help_text='Designates whether the user can log into this admin site.', verbose_name='staff status')),
                ('is_active', models.BooleanField(default=True, help_text='Designates whether this user should be treated as active. Unselect this instead of deleting accounts.', verbose_name='active')),
                ('date_joined', models.DateTimeField(default=django.utils.timezone.now, verbose_name='date joined')),
                ('groups', models.ManyToManyField(blank=True, related_name='custom_user_groups', to='auth.group')),
                ('user_permissions', models.ManyToManyField(blank=True, related_name='custom_user_permissions', to='auth.permission')),
            ],
            options={
                'verbose_name': 'user',
                'verbose_name_plural': 'users',
                'abstract': False,
            },
            managers=[
                ('objects', django.contrib.auth.models.UserManager()),
            ],
        ),
        migrations.CreateModel(
            name='HEI',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('hei_name', models.CharField(max_length=250)),
                ('country', models.CharField(max_length=250)),
                ('city', models.CharField(max_length=250)),
            ],
        ),
        migrations.CreateModel(
            name='Lecturer',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('lecturer_name', models.CharField(max_length=250)),
                ('title', models.CharField(choices=[('...', '...'), ('Dr.', 'Dr.'), ('Prof.', 'Prof.'), ('Assoc. Prof.', 'Assoc. Prof.'), ('Asst. Prof.', 'Asst. Prof.'), ('Lecturer', 'Lecturer'), ('Instructor', 'Instructor'), ('Teaching Assistant', 'Teaching Assistant')], max_length=20)),
                ('time_available', models.IntegerField(default=0)),
                ('courseOffered', models.ManyToManyField(blank=True, to='timeOptimizer.course')),
            ],
        ),
        migrations.CreateModel(
            name='optData',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('semesterName', models.CharField(max_length=250)),
                ('totalHours', models.DecimalField(decimal_places=2, max_digits=5)),
                ('optMethod', models.CharField(choices=[('product', 'product'), ('sum', 'sum'), ('sqrt', 'sqrt'), ('min', 'min')], max_length=10)),
                ('courses', models.JSONField(blank=True, default=dict)),
                ('created_at', models.DateTimeField(auto_now_add=True)),
                ('lecturer', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='timeOptimizer.lecturer')),
                ('picked_course', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, to='timeOptimizer.course')),
            ],
        ),
        migrations.CreateModel(
            name='semester',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('semester_name', models.CharField(max_length=250)),
                ('semesterModules', models.JSONField()),
                ('coursesInSemester', models.JSONField()),
            ],
        ),
        migrations.CreateModel(
            name='optResults',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('label', models.CharField(choices=[('veryGood', 'Very Good'), ('good', 'Good'), ('ok', 'Ok'), ('bad', 'Bad')], max_length=20)),
                ('semesterName', models.CharField(max_length=250)),
                ('totalHours', models.FloatField()),
                ('optMethod', models.CharField(max_length=10)),
                ('optimalValue', models.FloatField(default=0.0)),
                ('optimizationResults', models.JSONField()),
                ('optDataObj', models.OneToOneField(default=1, on_delete=django.db.models.deletion.CASCADE, related_name='opt_results', to='timeOptimizer.optdata')),
            ],
        ),
        migrations.AddField(
            model_name='lecturer',
            name='picked_semester',
            field=models.ManyToManyField(blank=True, to='timeOptimizer.semester'),
        ),
        migrations.AddField(
            model_name='lecturer',
            name='user',
            field=models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, related_name='lecturer_profile', to='timeOptimizer.customuser'),
        ),
        migrations.AddField(
            model_name='course',
            name='courseContent_data',
            field=models.OneToOneField(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, to='timeOptimizer.coursecontent'),
        ),
        migrations.AddField(
            model_name='course',
            name='courseDidactic_data',
            field=models.OneToOneField(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, to='timeOptimizer.coursedidactic'),
        ),
        migrations.AddField(
            model_name='course',
            name='courseImpact_data',
            field=models.OneToOneField(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, to='timeOptimizer.courseimpact'),
        ),
        migrations.AddField(
            model_name='course',
            name='courseLectTime_data',
            field=models.OneToOneField(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, to='timeOptimizer.courselecttime'),
        ),
        migrations.AddField(
            model_name='course',
            name='coursePresentation_data',
            field=models.OneToOneField(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, to='timeOptimizer.coursepresentation'),
        ),
        migrations.AddField(
            model_name='course',
            name='courseStudentCount_data',
            field=models.OneToOneField(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, to='timeOptimizer.coursestudentcount'),
        ),
    ]
