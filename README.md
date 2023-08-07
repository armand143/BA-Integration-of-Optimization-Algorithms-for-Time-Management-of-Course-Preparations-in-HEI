<<<<<<< HEAD
=======



>>>>>>> d824c712c2a3d2688a5b0c280e4bd9f30736722c
# Thesis Project: Integration of Optimization Algorithms for Time Management of Course Preparations in Higher Education

This repository contains an extension to an existing course optimization system that helps lecturers allocate preparation time for different aspects of their courses, such as content, didactic, and presentation. The extension adds a new optimization method based on weighted averages, complementing the existing four optimization methods. The system connects a Django web application with a Spring Boot backend to provide a seamless user experience.

## Introduction

The goal of this project is to enhance the existing course optimization system by adding a new optimization method based on weighted averages. This approach allows lecturers to assign weights to different aspects of their courses, such as content, didactic, and presentation, according to their importance. By considering these weights, the system allocates preparation time in a manner that caters to the preferences and requirements of the lecturer and the course.

## Features

- Extension of an existing course optimization system with a new weighted average method
- User-friendly interface to input course information and weights for each aspect
- Integration with Django for server-side data handling and Spring Boot for the optimization algorithm
- Support for multiple courses and custom aspect weights
- Detailed results for each course, including time allocation and percentage breakdowns

## Prerequisites

To run this project, you will need:

- Java Development Kit (JDK) 8 or higher
- Python 3.6 or higher
- Django 3.2 or higher
- Spring Boot 2.5 or higher
- Maven 3.8 or higher

## Installation

1. Clone the repository:

<<<<<<< HEAD
    ```
    git clone https://github.com/armand143/timeOptimizer.git
    ```
=======
Change to the project directory:
cd timeoptApp
>>>>>>> d824c712c2a3d2688a5b0c280e4bd9f30736722c

2. Change to the project directory:
    ```
    cd timeOptimizer
    ```

### Setting up the Django web application

<<<<<<< HEAD
1. In your Terminal, set up a Python virtual environment and activate it:

    ```
    python -m venv venv
=======
In your Terminal, set up a Python virtual environment and activate it:

python3 -m venv venv
source venv/Script/activate
>>>>>>> d824c712c2a3d2688a5b0c280e4bd9f30736722c

    source venv/bin/activate   # Use 'venv\Scripts\activate' on Windows
    ```

<<<<<<< HEAD
2. Navigate to the Django project directory (where the `requirements.txt` file is located) then install Django and other required Python packages:
=======
Navigate to the Django project directory (where the manage.py file is located):
cd timeoptApp/timeoptApp
>>>>>>> d824c712c2a3d2688a5b0c280e4bd9f30736722c

    ```
    cd webApp_django
    pip install -r requirements.txt
    ```

<<<<<<< HEAD
3. Run the Django migrations:
    ```
    python manage.py makemigrations
    python manage.py migrate
    ```
=======
In another terminal, navigate to the Spring Boot project directory(where the pom.xl file is located):
cd SpringAPIService/rest-serviceS/timeOpta
>>>>>>> d824c712c2a3d2688a5b0c280e4bd9f30736722c

### Setting up the Spring Boot backend

1. In another terminal, navigate to the Spring Boot project directory(where the `pom.xml` file is located):

    ```
    cd timeOptimizer_spring
    ```

2. Build the Spring Boot application using Maven:

    ```
    mvn clean install
    ```

## Usage

### Running the Django web application

1. Start the Django development server:
    ```
    python manage.py runserver
    ```

### Running the Spring Boot backend

<<<<<<< HEAD
1. On the separate terminal in the Spring Boot project directory run the Spring Boot application:
    ```
    mvn spring-boot:run
    ```
=======
on the separate terminal in the Spring Boot project directory run the Spring Boot application:
mvn spring-boot:run

>>>>>>> d824c712c2a3d2688a5b0c280e4bd9f30736722c

## Accessing the application

1. Open your web browser and navigate to the Django web application:
    ```
    http://127.0.0.1:8000/
    ```

2. On the registration page, create a new user  and you will be logged in automatically.

3. Once logged in, you'll be directed to the Profile Page. Here, you can:
   - Update your profile information
   - Select already available courses or create new ones

4. Start the optimization process by clicking on the big green button below.

5. On the next page, enter course parameter information in the provided form, along with an estimate of the time you think will be sufficient for each course. Tooltips are available on hover to provide more insight into each parameter.

6. Click the 'Overview' button to summarize all completed optimization runs.

7. On the next page, run the optimization process by clicking 'Optimize' to obtain the time allocation results.

8. Review the results by clicking on 'Summary'. This will display the results of all optimization strategies along with an evaluation done using Mean Absolute Deviation (MAD) and Standard Deviation (STD) metrics.



## Acknowledgements

- Prof. Fuhrmann for providing the roots of this Project. Credits go to him for the time allocation algorithm that's been used here. 
- My Advisor, Patrick Herbke, for his valuable feedback. 

## Support

If you encounter any issues or have questions, please open an issue on the GitHub repository.