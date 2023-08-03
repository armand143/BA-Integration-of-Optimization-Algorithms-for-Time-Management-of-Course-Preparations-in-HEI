


## Thesis Project: Integration of Optimization Algorithms for Time Management of Course Preparations in Higher Education

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

Clone the repository:
https://github.com/armand143/timeOptimizer.git

Change to the project directory:
cd timeoptApp [DJANGO]


## Setting up the Django web application

Set up a Python virtual environment and activate it:

```bash
python3 -m venv venv
source venv/bin/activate

Install Django and other required Python packages:
pip install -r requirements.txt

Navigate to the Django project directory:
cd timeoptApp [DJANGO]/timeoptApp

## Setting up the Spring Boot backend

Navigate to the Spring Boot project directory:
cd SpringAPIService/rest-serviceS/timeOpta

Build the Spring Boot application using Maven:
mvn clean install


## Usage

### Running the Django web application

Start the Django development server:
python manage.py runserver

### Running the Spring Boot backend

In a separate terminal, navigate to the Spring Boot project directory:
cd SpringAPIService/rest-serviceS/timeOpta

Run the Spring Boot application:
mvn spring-boot:run


## Accessing the application

Open your web browser and navigate to the Django web application:
http://127.0.0.1:8000/

Create a new user and log in. Once logged in, you'll be directed to the Profile Page, where you can update your profile information, select already available courses or create new ones, and start the optimization process by clicking on the big green button below.
On the next page, enter course parameter information in the provided form, along with an estimate of the time you think will be sufficient for each course. Tooltips are available on hover to provide more insight into each parameter.
Click the 'Overview' button to summarize all completed optimization runs. On the next page, run the optimization process (click 'Optimize') to obtain the time allocation results.
Review the results by clicking on 'Summary'. This will display the results of all optimization strategies along with an evaluation done using Mean Absolute Deviation (MAD) and Standard Deviation (STD) metrics.

    
#License

This project is licensed under the MIT License.


#Acknowledgements

- Prof. Fuhrmann for providing the roots of this Project. Credits go to him for the time allocation algorithm that's been used here. 
- My Advisor, Patrick Herbke, for his valuable feedback. 


#Support

If you encounter any issues or have questions, please open an issue on the GitHub repository.
