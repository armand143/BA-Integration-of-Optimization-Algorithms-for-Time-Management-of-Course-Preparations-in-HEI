from django.shortcuts import render
import requests
from django.shortcuts import render, redirect
from django.http import JsonResponse
from django.http import HttpResponse

from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from .serializers import optDataSerializer, optResultsSerializer, allCourses
from .models import HEI, course, lecturer, semester, courseContent, courseDidactic, coursePresentation, courseImpact, courseLectTime, courseStudentCount, optData, optResults
from .forms import courseForm, optDataForm, lecturerForm

import json
# from django_ajax.decorators import ajax
from django.core.paginator import Paginator

#page with all optimization data(course configurations entered by lecturers)
def home(request):
    optiData = optData.objects.all()
    serializer = optDataSerializer(optiData, many=True)
    return JsonResponse({'optimizationDataConfigs': serializer.data}, safe=False)

#this get's the opt_data's id and sends to spring boot api for optimization calculations (also creates the corresponding opt_results)
def optimizerWebService(request, optDataID):
    optData_ready = optData.objects.get(id = optDataID)
    serializer = optDataSerializer(optData_ready)
    #if serializer.is_valid():    #####not needed if you don't use "data attribute to instantiate the serializer" (see line before)
    json_obj = serializer.data
    print(json_obj)

    #     # create it's corresponding opt_results 
    #     obj_opt_results = optResults.objects.create(semesterName = optData_ready.semesterName,
    #                             totalHours = optData_ready.totalHours,
    #                             optMethod = optData_ready.optMethod,
    #                             optimalValue = 0.0, 
    #                             optimizationResults = {}, 
    #                             optDataObj = optData_ready)
        
    #     print(obj_opt_results.pk)
    #     print(obj_opt_results)
        
    # else: 
    corresponding_optresults = optResults.objects.filter(optDataObj = optData_ready)[0] #get the opt_result object for chosen opt_data
    url = 'http://localhost:8080/api/optimize'
    headers = {'Content-type': 'application/json'}
    response = requests.post(url, json = json_obj, headers = headers)

    if response.status_code == 200:

        display_response = json.loads(response.content.decode())  #response from spring boot web service

        optimization_results_json = {}

        for (k,v) in display_response['courses'].items():
            optimization_results_dic = {}
            optimization_results_dic[str(v['Course name'])] =  { 'Zeit Inhalt Anteil' : v['Zeit Inhalt Anteil'], 
                                                                'Zeit Inhalt Stunden': v['Zeit Inhalt Stunden'],
                                                                'Zeit Didaktik Anteil': v['Zeit Didaktik Anteil'],
                                                                'Zeit Didaktik Stunden': v['Zeit Didaktik Stunden'],
                                                                'Zeit Präsentation Anteil': v['Zeit Präsentation Anteil'],
                                                                'Zeit Präsentation Stunden': v['Zeit Präsentation Stunden']
            }
            optimization_results_json.update(optimization_results_dic)

        corresponding_optresults.optimizationResults = optimization_results_json #overwritting previous json object with new opt_results
        corresponding_optresults.save()          

        # allOptResponses_serialized = optResultsSerializer(optResults.objects.all(), many = True)
        context = { 'all_params': optData.objects.all(),
                    'all_results_serializer': optResults.objects.all()
                    }
        return render(request, 'optimizationPage.html', context)

    else:
        # Handle the error
        pass

        return HttpResponse("Something went wrong!")

    # else: 

    #     url = 'http://localhost:8080/api/optimize'
    #     headers = {'Content-type': 'application/json'}
    #     response = requests.post(url, json = json_obj, headers = headers)

    #     if response.status_code == 200:
    #         # Do something with the response

    #         optiData = optData.objects.all()
    #         # allOptData_serialized = optDataSerializer(optiData, many=True)
    #         display_response = json.loads(response.content.decode())

    #         optimization_results_json = {}

    #         for (k,v) in display_response['courses'].items():
    #             optimization_results_dic = {}
    #             optimization_results_dic[str(v['Course name'])] =  { 'Zeit Inhalt Anteil' : v['Zeit Inhalt Anteil'], 
    #                                                                 'Zeit Inhalt Stunden': v['Zeit Inhalt Stunden'],
    #                                                                 'Zeit Didaktik Anteil': v['Zeit Didaktik Anteil'],
    #                                                                 'Zeit Didaktik Stunden': v['Zeit Didaktik Stunden'],
    #                                                                 'Zeit Präsentation Anteil': v['Zeit Präsentation Anteil'],
    #                                                                 'Zeit Präsentation Stunden': v['Zeit Präsentation Stunden']
    #             }
    #             optimization_results_json.update(optimization_results_dic)

    #         opt_results = optResults(semesterName = display_response['Semester'],
    #                                 totalHours = display_response['Stundensumme'],
    #                                 optMethod = display_response['Optimierungsmethode'],
    #                                 optimalValue = display_response['Optimaler Wert'], 
    #                                 optimizationResults = optimization_results_json, 
    #                                 optDataObj = optData_ready)
    #         opt_results.save()              

    #         # allOptResponses_serialized = optResultsSerializer(optResults.objects.all(), many = True)
    #         context = { 'all_params_serializer': optiData,
    #                     'all_results_serializer': optResults.objects.all()
    #                     }
    #         return render(request, 'optimizationPage.html', context)

    #     else:
    #         # Handle the error
    #         pass

    #         return HttpResponse("Something went wrong!")




def createOptData(request):
    form = optDataForm()


    if request.method == 'POST':
        form = optDataForm(request.POST)
        if form.is_valid():
            obj = form.save()

            print(obj.pk)

            data = {}
            course_config = {'courseContent': {'CourseComplexity': 0, 'CourseFamiliarity': 0},
                             'courseDidactic': {'CourseComplexity': 0, 'CourseFamiliarity': 0},
                             'coursePresentation': {'finished': 0, 'time0': 0, 'pres0': 0, 'complexity': 0},
                             'courseImpact': {'d': 0},
                             'courseLectTime': {'d': 0},
                             'courseStudentCount': {'students': 0},
                             }

            course_config['courseContent']['CourseComplexity'] = request.POST['courseContentComplexity']
            course_config['courseContent']['CourseFamiliarity'] = request.POST['courseContentFamiliarity']

            course_config['courseDidactic']['CourseComplexity'] = request.POST['courseDidacticComplexity']
            course_config['courseDidactic']['CourseFamiliarity'] = request.POST['courseDidacticFamiliarity']

            course_config['coursePresentation']['finished'] = request.POST['coursePresentationFinished']
            course_config['coursePresentation']['time0'] = request.POST['coursePresentationTime0']
            course_config['coursePresentation']['pres0'] = request.POST['coursePresentationPres0']
            course_config['coursePresentation']['complexity'] = request.POST['coursePresentationComplexity']

            course_config['courseImpact']['d'] = request.POST['courseImpact']

            course_config['courseLectTime']['d'] = request.POST['courseLectTime']

            course_config['courseStudentCount']['students'] = request.POST['courseStudentCount']

            data[str(obj.picked_course)] = course_config

            obj.courses = data
            obj.save()
            print(obj.pk)

            #obj, obj_opt_results = optData.objects.create(semesterName = obj.semesterName, totalHours = obj.totalHours, optMethod = obj.optMethod, courses = data), optResults.objects.create(semesterName = obj.semesterName, totalHours = obj.totalHours, optMethod = obj.optMethod, optimalValue = 0.0, optimizationResults = {}, optDataObj = obj)


            # create it's corresponding opt_results 
            obj_opt_results = optResults.objects.create(semesterName = obj.semesterName,
                                    totalHours = obj.totalHours,
                                    optMethod = obj.optMethod,
                                    optimalValue = 0.0, 
                                    optimizationResults = {}, 
                                    optDataObj = obj)
            
            obj_opt_results.save()     
            print("this is the new pk: " + str(obj_opt_results.pk))  

            form = optDataForm()
            context = {'form': form,
                       'course_configurations': optData.objects.all()
                       }
            return render(request, 'optData.html', context)
        else:
            form = optDataForm()
            return render(request, 'optData.html', {'form': form})
        # return HttpResponse("View did not return any content.")
    context = {'form': form}
    return render(request, 'optData.html', context ) 
    


def createCourse(request):
    if request.method == 'POST':
        newCourse = courseForm(request.POST)
        if newCourse.is_valid():
            newCourse.save()
            return HttpResponse("Success!")
    else:
        form = courseForm()
        return render(request, 'createCourse.html', {'form': form})


def createLecturer(request):
    if request.method == 'POST':
        form = lecturerForm(request.POST)
        if form.is_valid():
            form.save()
    else:
        form = lecturerForm()
        return render(request, 'createLecturer.html', {'form': form})


def addCourseParameter(request, id):
    instance_obj = optData.objects.get(id = id)

    instanceForm = optDataForm(instance=optData.objects.get(pk = id))

    if request.method == 'POST':
        instance_obj.totalHours = request.POST['totalHours']
        instance_obj.semesterName = request.POST['semesterName']
        instance_obj.optMethod = request.POST['optMethod']

        # notice request.POST['picked_course'] returns "id" of a course-> we're going to get the id from all objects of courses
        selected_course = course.objects.get(id=int(request.POST['picked_course']))

        instance_obj.picked_course = selected_course

        json_data = instance_obj.courses
        key = instance_obj.picked_course.course_name

        if key not in json_data :
            data = {}

            course_config = {'courseContent': {'CourseComplexity': 0, 'CourseFamiliarity': 0},
                            'courseDidactic': {'CourseComplexity': 0, 'CourseFamiliarity': 0},
                            'coursePresentation': {'finished': 0, 'time0': 0, 'pres0': 0, 'complexity': 0},
                            'courseImpact': {'d': 0},
                            'courseLectTime': {'d': 0},
                            'courseStudentCount': {'students': 0},
                            }

            course_config['courseContent']['CourseComplexity'] = request.POST['courseContentComplexity']
            course_config['courseContent']['CourseFamiliarity'] = request.POST['courseContentFamiliarity']

            course_config['courseDidactic']['CourseComplexity'] = request.POST['courseDidacticComplexity']
            course_config['courseDidactic']['CourseFamiliarity'] = request.POST['courseDidacticFamiliarity']

            course_config['coursePresentation']['finished'] = request.POST['coursePresentationFinished']
            course_config['coursePresentation']['time0'] = request.POST['coursePresentationTime0']
            course_config['coursePresentation']['pres0'] = request.POST['coursePresentationPres0']
            course_config['coursePresentation']['complexity'] = request.POST['coursePresentationComplexity']

            course_config['courseImpact']['d'] = request.POST['courseImpact']

            course_config['courseLectTime']['d'] = request.POST['courseLectTime']

            course_config['courseStudentCount']['students'] = request.POST['courseStudentCount']

            data[key] = course_config

            json_data.update(data)

            #adjusting JSONField's structure to look more spaced and readable   
            #Notice this works, it gives it the original structure(ordered respected) you defined, but it's not displayed on screen with identations/spacing
            # organized_json_data = json.dumps(json_data, indent=4, sort_keys=True)

            instance_obj.courses = json_data
            instance_obj.save()            

            form = optDataForm()
            context = {'form': form,
                    'all_params': optData.objects.all(),
                    }
            return render(request, 'optimizationPage.html', context)
        else:
                context = {'form': instanceForm}
                return render(request, 'optData.html', context ) 

    context = {'form': instanceForm}
    return render(request, 'optData.html', context ) 


def allCourseParameters(request):
    optiData = optData.objects.all()
    context = {'all_params': optiData}

    return render(request, 'optimizationPage.html', context)

# @ajax
# def get_results(request, res_id):
#     res_courses = optResults.objects.get(id = res_id)
#     data = res_courses.optimizationResults['courses']