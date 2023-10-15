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

# # Applying database migrations
# # This runs at build time, consider running it at start time
# RUN python manage.py migrate

# (Optional) Command to run the application using Gunicorn
CMD ["sh", "-c", "python manage.py migrate && gunicorn timeoptApp.wsgi:application --bind 0.0.0.0:8000 --timeout 1200"]

