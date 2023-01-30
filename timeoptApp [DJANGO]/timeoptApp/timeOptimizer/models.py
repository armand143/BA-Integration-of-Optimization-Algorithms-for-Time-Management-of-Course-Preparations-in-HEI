from django.db import models

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
    courseNum = models.IntegerField()
    course_credits = models.IntegerField()

    courseContent_data = models.OneToOneField(courseContent, on_delete=models.SET_NULL, null = True, blank = True)
    courseDidactic_data = models.OneToOneField(courseDidactic, on_delete=models.SET_NULL, null = True, blank = True)
    coursePresentation_data = models.OneToOneField(coursePresentation, on_delete=models.SET_NULL, null = True, blank = True)
    courseImpact_data = models.OneToOneField(courseImpact, on_delete=models.SET_NULL, null = True, blank = True)
    courseLectTime_data = models.OneToOneField(courseLectTime, on_delete=models.SET_NULL, null = True, blank = True)
    courseStudentCount_data = models.OneToOneField(courseStudentCount, on_delete=models.SET_NULL, null = True, blank = True)

    def __str__(self):
        return self.course_name

class lecturer(models.Model):
    lecturer_name = models.CharField(max_length=250)
    lecturer_title = models.CharField(max_length=250)
    courseOffered = models.ManyToManyField(course, null=True, blank=True)

    def __str__(self):
        return self.lecturer_title + " " + self.lecturer_name

class semester(models.Model):
    semester_name = models.CharField(max_length=250)
    semesterModules = models.JSONField() #Should be used to control to the availability of different courses
    coursesInSemester = models.JSONField()

class optData(models.Model):
    OptimizationMethods = [
        ('product', 'product'),
        ('sum', 'sum'),
        ('sqrt', 'sqrt'),
        ('min', 'min'),
    ]
    semesterName = models.CharField(max_length=250)
    totalHours = models.DecimalField(max_digits=5, decimal_places=2)
    optMethod = models.CharField(max_length=10, choices=OptimizationMethods)
    courses = models.JSONField(default={}, null=False, blank=True) #add all courses with configAttributes iteratively here, as json dict
    lecturer = models.ForeignKey(lecturer, on_delete=models.CASCADE)
    picked_course = models.ForeignKey(course, on_delete=models.CASCADE, null=True, blank=True)
    # counter = models.IntegerField(default=0)
    created_at = models.DateTimeField(auto_now_add=True)
    #picked_course = models.ManyToManyField(course, null = True, blank=True)
    # lecturer = models.OneToOneField(lecturer, on_delete=models.CASCADE)
    
    # def save(self, *args, **kwargs):
    #     if not self._state.adding:
    #         self.counter += 1
    #         super().save(*args, **kwargs)

    def __str__(self):
        return  "/" + self.semesterName + "/" + self.optMethod + "/" + str(self.lecturer)


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
        optMethod = models.CharField(max_length=10)
        optimalValue = models.FloatField(default=0.0)
        # lecturer = models.ForeignKey(lecturer, on_delete=models.CASCADE)
        optimizationResults = models.JSONField()
        optDataObj = models.OneToOneField(optData, on_delete=models.CASCADE, related_name='opt_results', default=1)