from django.contrib import admin
from .models import HEI, course, lecturer, semester, courseContent, courseDidactic, coursePresentation, courseImpact, courseLectTime, courseStudentCount, optData, optResults

# Register your models here.
admin.site.register([HEI, course, lecturer, semester, courseContent, courseDidactic, coursePresentation, courseImpact, courseLectTime, courseStudentCount, optData, optResults])