from django import forms
from .models import HEI, course, lecturer, semester, courseContent, courseDidactic, coursePresentation, courseImpact, courseLectTime, courseStudentCount, optData, optResults
from jsonschema import validate, ValidationError


class courseForm(forms.ModelForm):
    class Meta:
        model = course
        fields = '__all__'


class lecturerForm(forms.ModelForm):
    class Meta:
        model = lecturer
        fields = '__all__'


class optDataForm(forms.ModelForm):
        courseContentComplexity = forms.DecimalField(
        max_digits=2, decimal_places=2, widget=forms.TextInput(attrs={'class': 'form-control'}))
        courseContentFamiliarity = forms.DecimalField(max_digits=2, decimal_places=2, widget=forms.TextInput(attrs={'class': 'form-control'}))

        courseDidacticComplexity = forms.DecimalField(max_digits=2, decimal_places=2, widget=forms.TextInput(attrs={'class': 'form-control'}))
        courseDidacticFamiliarity = forms.DecimalField(max_digits=2, decimal_places=2, widget=forms.TextInput(attrs={'class': 'form-control'}))

        coursePresentationFinished = forms.DecimalField(max_digits=2, decimal_places=2, widget=forms.TextInput(attrs={'class': 'form-control'}))
        coursePresentationTime0 = forms.DecimalField(max_digits=2, decimal_places=2, widget=forms.TextInput(attrs={'class': 'form-control'}))
        coursePresentationPres0 = forms.DecimalField(max_digits=2, decimal_places=2, widget=forms.TextInput(attrs={'class': 'form-control'}))
        coursePresentationComplexity = forms.DecimalField(max_digits=2, decimal_places=2, widget=forms.TextInput(attrs={'class': 'form-control'}))

        courseImpact = forms.DecimalField(max_digits=2, decimal_places=2, widget=forms.TextInput(attrs={'class': 'form-control'}))
        
        courseLectTime = forms.DecimalField(max_digits=2, decimal_places=2, widget=forms.TextInput(attrs={'class': 'form-control'}))

        courseStudentCount = forms.IntegerField(widget=forms.TextInput(attrs={'class': 'form-control'}))

        # picked_course = forms.ModelMultipleChoiceField( queryset=course.objects.all(),widget=forms.SelectMultiple(attrs={'size':'4'}))

        class Meta:
            model = optData
            fields = ('semesterName', 'totalHours', 'optMethod', 'lecturer', 'picked_course', 'courseContentComplexity', 'courseContentFamiliarity', 'courseDidacticComplexity', 'courseDidacticFamiliarity', 'coursePresentationFinished', 'coursePresentationTime0', 'coursePresentationPres0', 'coursePresentationComplexity', 'courseImpact', 'courseLectTime', 'courseStudentCount')

            semesterName = forms.CharField(max_length=255, widget=forms.TextInput(attrs={'class': 'form-control'}))
            totalHours = forms.EmailField(widget=forms.EmailInput(attrs={'class': 'form-control'}))
            optMethod = forms.CharField(widget=forms.Textarea(attrs={'class': 'form-control'}))
            lecturer = forms.CharField(max_length=255, widget=forms.TextInput(attrs={'class': 'form-control'}))
            picked_course = forms.CharField(max_length=255, widget=forms.TextInput(attrs={'class': 'form-control'}))



