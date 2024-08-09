# Use the official MongoDB image from Docker Hub
FROM mongo:latest

# Copy the initialization script to the container
COPY init-mongo.js /docker-entrypoint-initdb.d/

# Expose MongoDB port
EXPOSE 27017
