from rest_framework import serializers
from .models import HEI, course, lecturer, semester, courseContent, courseDidactic, coursePresentation, courseImpact, courseLectTime, courseStudentCount, optData, optResults

class optDataSerializer(serializers.ModelSerializer):
    class Meta:
        model = optData
        fields = '__all__'

class optResultsSerializer(serializers.ModelSerializer):
    class Meta:
        model = optResults
        fields = '__all__' 


class allCourses(serializers.ModelSerializer):
    class Meta:
        model = course
        fields = '__all__'