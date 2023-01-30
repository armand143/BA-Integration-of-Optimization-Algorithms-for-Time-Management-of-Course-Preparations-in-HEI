from . import views
from django.urls import path

urlpatterns = [
    path('', views.home, name='home'),
    path('createCourse/', views.createCourse , name='createCourse'),
    path('createOptData/', views.createOptData, name= 'createOptData'),
    path('allCourseParameters/', views.allCourseParameters, name='allCourseParameters'),
    path('addCourseParameter/<int:id>/', views.addCourseParameter, name = 'addCourseParameter'),
    path('optimizerWebService/<int:optDataID>/', views.optimizerWebService, name='optimizerWebService'),

    
]