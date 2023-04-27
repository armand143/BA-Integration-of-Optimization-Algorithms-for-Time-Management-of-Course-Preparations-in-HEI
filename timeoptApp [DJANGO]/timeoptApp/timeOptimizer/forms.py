from django import forms
from .models import HEI, course, CustomUser, Lecturer, semester, courseContent, courseDidactic, coursePresentation, courseImpact, courseLectTime, courseStudentCount, optData, optResults
from jsonschema import validate, ValidationError
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm
from django.contrib.auth.models import User, Group
from decimal import Decimal
from django.core.validators import MinValueValidator, MaxValueValidator


# class lecturerRegistrationForm(UserCreationForm):
#     title = forms.ChoiceField(choices=lecturer.TITLE_CHOICES)
#     first_name = forms.CharField(max_length=30, required=False, initial="Default")
#     last_name = forms.CharField(max_length=30, required=False, initial="Default")

#     class Meta:
#         model = User
#         fields = ('username', 'title', 'email', 'password1', 'password2')

#     def save(self, commit = True):
#         user = super(lecturerRegistrationForm, self).save(commit=False)

#         if lecturer.objects.filter(username=self.cleaned_data['username']).exists():
#             raise ValidationError("Username already exists.")
#         else:
#             user.save()
#             new_lecturer = lecturer.objects.create(user = user, lecturer_name = self.cleaned_data['username'], title = self.cleaned_data['title'])
#         return user
    
class LecturerUpdateForm(forms.ModelForm):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.fields['picked_semester'].initial = self.instance.picked_semester

    class Meta:
        model = Lecturer
        fields = ['lecturer_name', 'title', 'lecturer_email', 'picked_semester', 'time_available']





class lecturerRegistrationForm(UserCreationForm):
    title = forms.ChoiceField(choices=Lecturer.TITLE_CHOICES)

    class Meta:
        model = CustomUser
        fields = ('username', 'title', 'email', 'password1', 'password2')
       
    def save(self, commit=True):
        user = super(lecturerRegistrationForm, self).save(commit=False)
        # user.email = self.cleaned_data['email']
        user.set_password(self.cleaned_data['password1'])
        # user.save()
        if commit:
            user.save()
            new_lecturer = Lecturer.objects.create(user=user, lecturer_name=self.cleaned_data['username'], title=self.cleaned_data['title'], lecturer_email = user.email)
            new_lecturer.save()

        return user


class LoginForm(AuthenticationForm):
     class Meta:
          model = User
          fields = ('username', 'password')

class courseForm(forms.ModelForm):
        course_name = forms.CharField(widget=forms.TextInput(attrs={'size': '40', 'class': 'form-control'}))
        course_credits = forms.IntegerField(widget=forms.TextInput(attrs={'class': 'form-control'}))
        course_description = forms.CharField(widget=forms.Textarea(attrs={'rows': 3, 'cols': 40, 'class': 'form-control'}))

        class Meta: 
            model = course
            fields = ('course_name', 'course_credits', 'course_description')


# class semesterTimeForm(forms.ModelForm):
#     def __init__(self, *args, **kwargs):
#             super().__init__(*args, **kwargs)
#             self.fields['picked_semester'].initial = self.instance.picked_semester

#     time_available = forms.IntegerField(
#         widget=forms.NumberInput(attrs={'size': '30'}))
    
#     class Meta:
#         model = Lecturer
#         fields = ['picked_semester', 'time_available']
#         # widgets = {
#         #     'picked_semester': forms.Select(attrs={'class': 'form-control'}),
#         # }
#         picked_semester = forms.ModelChoiceField(queryset = semester.objects.all(), widget=forms.Select(attrs={'class': 'form-control'}))
     

class optDataForm(forms.ModelForm):
        courseContentComplexity = forms.IntegerField(validators=[MinValueValidator(1), MaxValueValidator(20)], widget=forms.TextInput(attrs={'class': 'form-control', 'type': 'number', 'step': '0.01', 'min': '1.0', 'max': '20.0'}))
        courseContentFamiliarity = forms.DecimalField(max_digits=5, decimal_places=2, min_value=Decimal('0.0'), max_value=Decimal('1.0'), widget=forms.TextInput(attrs={'class': 'form-control'}))
        courseContentWeight = forms.IntegerField(validators=[MinValueValidator(0), MaxValueValidator(3)], widget=forms.TextInput(attrs={'class': 'form-control', 'type': 'number', 'step': '1', 'min': '1.0', 'max': '3.0'}))

        courseDidacticComplexity = forms.IntegerField(validators=[MinValueValidator(1), MaxValueValidator(20)], widget=forms.TextInput(attrs={'class': 'form-control'}))
        courseDidacticFamiliarity = forms.DecimalField(max_digits=5, decimal_places=2, min_value=Decimal('0.0'), max_value=Decimal('1.0'), widget=forms.TextInput(attrs={'class': 'form-control'}))
        courseDidacticWeight = forms.IntegerField(validators=[MinValueValidator(0), MaxValueValidator(3)], widget=forms.TextInput(attrs={'class': 'form-control', 'type': 'number', 'step': '1', 'min': '1.0', 'max': '3.0'}))

        coursePresentationFinished = forms.DecimalField(max_digits=5, decimal_places=2, min_value=Decimal('0.0'), max_value=Decimal('1.0'), widget=forms.TextInput(attrs={'class': 'form-control'}))
        coursePresentationTime0 = forms.DecimalField(max_digits=5, decimal_places=2, min_value=Decimal('0.0'), max_value=Decimal('1.0'), widget=forms.TextInput(attrs={'class': 'form-control'}))
        coursePresentationPres0 = forms.DecimalField(max_digits=5, decimal_places=2, min_value=Decimal('0.0'), max_value=Decimal('1.0'), widget=forms.TextInput(attrs={'class': 'form-control'}))
        coursePresentationComplexity = forms.IntegerField(validators=[MinValueValidator(1), MaxValueValidator(20)], widget=forms.TextInput(attrs={'class': 'form-control'}))
        courseImpact = forms.DecimalField(max_digits=2, decimal_places=1, min_value=Decimal('1.0'), max_value=Decimal('2.0'), widget=forms.TextInput(attrs={'class': 'form-control'}))
        coursePresentationWeight = forms.IntegerField(validators=[MinValueValidator(0), MaxValueValidator(3)], widget=forms.TextInput(attrs={'class': 'form-control', 'type': 'number', 'step': '1', 'min': '1.0', 'max': '3.0'}))

        courseLectTime = forms.DecimalField(max_digits=4, decimal_places=1, min_value=Decimal('0.5'), max_value=Decimal('40.0'), widget=forms.TextInput(attrs={'class': 'form-control'}))

        courseStudentCount = forms.IntegerField(widget=forms.TextInput(attrs={'class': 'form-control'}))


        class Meta:
            model = optData
            fields = ('optMethod','picked_course', 'courseContentComplexity', 'courseContentFamiliarity', 'courseDidacticComplexity', 'courseDidacticFamiliarity', 'coursePresentationFinished', 'coursePresentationTime0', 'coursePresentationPres0', 'coursePresentationComplexity', 'courseImpact', 'courseLectTime', 'courseStudentCount')


            optMethod = forms.CharField(widget=forms.Textarea(attrs={'class': 'form-control'}))

            picked_course = forms.CharField(max_length=255, widget=forms.TextInput(attrs={'class': 'form-control'}))



