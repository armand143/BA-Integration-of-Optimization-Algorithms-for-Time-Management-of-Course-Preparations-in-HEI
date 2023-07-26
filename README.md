# BA-Integration-of-Optimization-Algorithms-for-Time-Management-of-Course-Preparations-in-HEI 

#Thesis Project: Integration of Optimization Algorithms for Time Management of Course Preparations in Higher Education

This repository contains an extension to an existing course optimization system that helps lecturers allocate preparation time for different aspects of their courses, such as content, didactic, and presentation. The extension adds a new optimization method based on weighted averages, complementing the existing four optimization methods. The system connects a Django web application with a Spring Boot backend to provide a seamless user experience.
Introduction

The goal of this project is to enhance the existing course optimization system by adding a new optimization method based on weighted averages. This approach allows lecturers to assign weights to different aspects of their courses, such as content, didactic, and presentation, according to their importance. By considering these weights, the system allocates preparation time in a manner that caters to the preferences and requirements of the lecturer and the course.
Features

    Extension of an existing course optimization system with a new weighted average method
    User-friendly interface to input course information and weights for each aspect
    Integration with Django for server-side data handling and Spring Boot for the optimization algorithm
    Support for multiple courses and custom aspect weights
    Detailed results for each course, including time allocation and percentage breakdowns

Prerequisites:

To run this project, you will need:

    Java Development Kit (JDK) 8 or higher
    Python 3.6 or higher
    Django 3.2 or higher
    Spring Boot 2.5 or higher
    Maven 3.8 or higher

Installation
Clone the repository:

    git clone https://github.com/yourusername/course-optimization-extension.git

Change to the project directory:

    cd course-optimization-extension

Setting up the Django web application
Set up a Python virtual environment and activate it:

    python3 -m venv venv
    source venv/bin/activate

Install Django and other required Python packages:

    pip install -r requirements.txt

Navigate to the Django project directory:

    cd django_project

Apply migrations:

    python manage.py migrate

Create a superuser to access the admin panel:

    python manage.py createsuperuser

Setting up the Spring Boot backend
Navigate to the Spring Boot project directory:

    cd ../spring_boot_project

Build the Spring Boot application using Maven:

    mvn clean install

Usage
Running the Django web application
Start the Django development server:

    python manage.py runserver

Running the Spring Boot backend
In a separate terminal, navigate to the Spring Boot project directory:

    cd spring_boot_project
    
Run the Spring Boot application:

    mvn spring-boot:run

Accessing the application
Open your web browser and navigate to the Django web application:

    http://127.0.0.1:8000/

Log in with the superuser credentials you created earlier.
Enter course information, aspect weights, and other relevant parameters.
Choose the weighted average optimization method or one of the other four available methods.
Run the optimization process to obtain the time allocation results.
Review the results and adjust the weights or other parameters as needed.

Contributing
If you would like to contribute to this project, please follow these steps:

    Fork the repository
    Create a new branch (git checkout -b your-feature-branch)
    Commit your changes (git commit -am 'Add some feature')
    Push to the branch (git push origin your-feature-branch)
    Create a new pull request
    
License
This project is licensed under the MIT License.
Acknowledgements
    - Prof Fuhrmann for providing the roots of this Project. Credits go to him for the time allocation algorithm that's been used here.   

Support
If you encounter any issues or have questions, please open an issue on the GitHub repository.
