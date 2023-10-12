# # Use an official Python runtime as a base image
# FROM python:3.9-slim-buster

# # Set the working directory
# WORKDIR /app

# # Copy the current directory contents into the container
# COPY . .

# # Install any needed packages specified in requirements.txt
# RUN pip install --no-cache-dir -r requirements.txt

# # Make port 8000 available to the world outside this container
# EXPOSE 8000

# # Define environment variables
# ENV DEBUG=False

# # Run app.py when the container launches
# CMD ["/bin/bash"]
# # CMD ["gunicorn", "timeoptApp.timeoptApp.wsgi:application", "--bind", "0.0.0.0:8000"]




# Use the Python 3.9 image
FROM python:3.9

# Set environment variables
ENV PYTHONUNBUFFERED=1

# Set the working directory in the docker container
WORKDIR /app/

# Copy the current directory contents into the container at /app/
COPY . .

# Install the project requirements
RUN pip install -r requirements.txt

# Run collectstatic command
RUN python manage.py collectstatic --noinput

# (Optional) Command to run the application using Gunicorn
CMD ["gunicorn", "timeoptApp.wsgi:application", "--bind", "0.0.0.0:8000"]


