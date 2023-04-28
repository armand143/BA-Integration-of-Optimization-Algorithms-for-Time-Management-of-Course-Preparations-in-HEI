from django.db import models
from django.contrib.auth.models import User, AbstractUser, Group, Permission
from django.utils import timezone

# Create your models here.

class HEI(models.Model):
    hei_name = models.CharField(max_length=250)
    country = models.CharField(max_length=250)
    city = models.CharField(max_length=250)

class courseContent(models.Model):
    # course = models.OneToOneField(course, on_delete=models.CASCADE)
    CourseComplexity = models.DecimalField(max_digits=2, decimal_places=2)
    CourseFamiliarity = models.DecimalField(max_digits=2, decimal_places=2)

class courseDidactic(models.Model):
    # course = models.OneToOneField(course, on_delete=models.CASCADE)
    CourseComplexity = models.DecimalField(max_digits=2, decimal_places=2)
    CourseFamiliarity = models.DecimalField(max_digits=2, decimal_places=2)

class coursePresentation(models.Model):
    # course = models.OneToOneField(course, on_delete=models.CASCADE)
    finished = models.DecimalField(max_digits=2, decimal_places=2)
    time0 = models.DecimalField(max_digits=2, decimal_places=2)
    pres0 = models.DecimalField(max_digits=2, decimal_places=2)
    complexity = models.DecimalField(max_digits=2, decimal_places=2)

class courseImpact(models.Model):
    # course = models.OneToOneField(course, on_delete=models.CASCADE)
    d = models.DecimalField(max_digits=2, decimal_places=2)

class courseLectTime(models.Model):
    # course = models.OneToOneField(course, on_delete=models.CASCADE)
    d = models.DecimalField(max_digits=2, decimal_places=2)

class courseStudentCount(models.Model):
    # course = models.OneToOneField(course, on_delete=models.CASCADE)
    students = models.IntegerField()

class course(models.Model):
    course_name = models.CharField(max_length=250)
    course_credits = models.IntegerField()
    course_description = models.TextField(default="No description available")
    lecturer_name = models.CharField(max_length=250, default="Anonymous Lecturer")

    def __str__(self):
        return self.course_name

class semester(models.Model):
    semester_name = models.CharField(max_length=250)
    semesterModules = models.JSONField() #Should be used to control to the availability of different courses
    coursesInSemester = models.JSONField()

    def __str__(self):
        return self.semester_name
    


class CustomUser(AbstractUser):
    groups = models.ManyToManyField(Group, related_name="custom_user_groups", blank=True)
    user_permissions = models.ManyToManyField(Permission, related_name="custom_user_permissions", blank=True)


class Lecturer(models.Model):
    TITLE_CHOICES = [
        ('...', '...'),
        ('Dr.', 'Dr.'),
        ('Prof.', 'Prof.'),
        ('Assoc. Prof.', 'Assoc. Prof.'),
        ('Asst. Prof.', 'Asst. Prof.'),
        ('Lecturer', 'Lecturer'),
        ('Instructor', 'Instructor'),
        ('Teaching Assistant', 'Teaching Assistant'),
    ]
    lecturer_name = models.CharField(max_length=250)
    title = models.CharField(max_length=20, choices=TITLE_CHOICES)
    courseOffered = models.ManyToManyField(course, blank=True)
    user = models.OneToOneField(CustomUser, on_delete=models.CASCADE, related_name='lecturer_profile')
    picked_semester = models.ManyToManyField(semester, blank=True)
    time_available = models.IntegerField(default=0)
    profile_picture = models.ImageField(upload_to="profile_pics", default= "default_pro_pic.jpg")
    lecturer_email = models.CharField(max_length=250, default="foobar@jetmail.com")
    
    def __str__(self):
        return self.title + " " + self.lecturer_name



class optData(models.Model):
    OptimizationMethods = [
        ('product', 'product'),
        ('sum', 'sum'),
        ('sqrt', 'sqrt'),
        ('min', 'min'),
        ('weightedAverage', 'weightedAverage'),
    ]
    semesterName = models.CharField(max_length=250)
    totalHours = models.DecimalField(default = 0, max_digits=5, decimal_places=2)
    optMethod = models.CharField(max_length=20, choices=OptimizationMethods)
    courses = models.JSONField(default=dict, null=False, blank=True) #add all courses with configAttributes iteratively here, as json dict
    lecturer = models.ForeignKey(Lecturer, on_delete=models.CASCADE, default=None)
    picked_course = models.ForeignKey(course, on_delete=models.CASCADE, null=True, blank=True)
    # counter = models.IntegerField(default=0)
    created_at = models.DateTimeField(auto_now_add=True)
    edited_at = models.DateTimeField(auto_now=True)
    status = models.CharField(max_length=50, default='default_value')

    def __str__(self):
        return  "/" + self.semesterName + "/" + self.optMethod + "/" + str(self.lecturer)
    
    # def save(self, *args, **kwargs):
    #     self.edited_at = timezone.now()
    #     super().save(*args, **kwargs)


class optResults(models.Model):
        labelChoices = [
        ('veryGood', 'Very Good'),
        ('good', 'Good'),
        ('ok', 'Ok'),
        ('bad', 'Bad'),
        ]
        label = models.CharField(max_length=20, choices=labelChoices)
        semesterName = models.CharField(max_length=250)
        totalHours = models.FloatField()
        optMethod = models.CharField(max_length=20)
        optimalValue = models.FloatField(default=0.0)
        # lecturer = models.ForeignKey(lecturer, on_delete=models.CASCADE)
        optimizationResults = models.JSONField()
        optDataObj = models.OneToOneField(optData, on_delete=models.CASCADE, related_name='opt_results', default=1)
        created_at = models.DateTimeField(auto_now_add=True)
        edited_at = models.DateTimeField(auto_now=True)
        status = models.CharField(max_length=50, default='default_value')
        update_status = models.CharField(max_length=50, default='default_value')

