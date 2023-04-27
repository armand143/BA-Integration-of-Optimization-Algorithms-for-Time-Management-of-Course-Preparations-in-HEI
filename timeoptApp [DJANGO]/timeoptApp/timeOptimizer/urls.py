from . import views
from django.urls import path

urlpatterns = [
    path('', views.home, name='home'),
    #path('createCourse/', views.createCourse , name='createCourse'),
    path('createOptData/', views.createOptData, name= 'createOptData'),
    path('allCourseParameters/', views.allCourseParameters, name='allCourseParameters'),
    path('addCourseParameter/<int:id>/', views.addCourseParameter, name = 'addCourseParameter'),
    path('optimizerWebService/<int:optDataID>/', views.optimizerWebService, name='optimizerWebService'),
    path('updateForm/', views.updateForm, name = 'updateForm'),
    path('editCourse/<int:optData_id><str:key>/', views.editCourse, name = 'editCourse'),
    path('login_view/', views.login_view, name = 'login_view'),
    path('register_view/', views.register_view, name = 'register_view'),
    path('deleteCourse/<int:course_id>/', views.deleteCourse, name = 'deleteCourse'),
    path('profile_view/', views.profile_view, name = 'profile_view'), 
    path('config_page/', views.config_page, name = 'config_page'),
    path('logout_view/', views.logout_view, name = 'logout_view'),
    path('delete_optCourse/<int:optData_id><str:key>/', views.delete_optCourse, name = 'delete_optCourse'),
    path('delete_optData/<int:optData_id>/', views.delete_optData, name = 'delete_optData'),
    path('addCourse/<int:cour_id>/', views.addCourse, name = 'addCourse'),
    path('optimizationOverview/<int:optData_id>/', views.optimizationOverview, name = 'optimizationOverview'),
    path('editCourseDetail/<int:course_id>/', views.editCourseDetail, name = 'editCourseDetail'),



]

