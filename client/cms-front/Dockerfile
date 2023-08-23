# Use the official Node.js image as the base image
FROM node:16

# Set the working directory within the container
WORKDIR /angular

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the app files to the working directory
COPY . .

# Expose the port your Angular app runs on (default is 4200)
EXPOSE 4200

# Start the Angular app when the container starts
CMD ["npm", "start"]
