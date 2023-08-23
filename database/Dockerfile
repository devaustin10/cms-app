# Use the official MySQL image as the base image
FROM mysql:latest

# Specify the root password for MySQL
ENV MYSQL_ROOT_PASSWORD=top-secret-password

# Create a directory within the container to copy schema files
WORKDIR /docker-entrypoint-initdb.d

# Copy your schema SQL files from the local 'schemas/' directory to the container
COPY cms-prod.sql cms-test.sql ./

# Expose MySQL port (default is 3306)
EXPOSE 3306

# Command to start the MySQL server
CMD ["mysqld"]

# docker build -t mysql-with-schemas .
